package com.xry.job.elasticjob.service.impl;


import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.internal.config.LiteJobConfigurationGsonFactory;
import com.dangdang.ddframe.job.lite.internal.storage.JobNodePath;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobAPIFactory;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.ShardingStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.lite.lifecycle.domain.ShardingInfo;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;

import com.google.common.base.Optional;
import com.xry.job.elasticjob.service.ElasticJobService;
import com.xry.job.elasticjob.service.bo.JobPacketBO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * @Author JH.TAO
 * @create 2017-12-28 14:33
 */
@Service
@Slf4j
public class ElasticJobServiceImpl implements ElasticJobService {

    @Autowired
    private CoordinatorRegistryCenter registryCenter;

    @Autowired
    private ZookeeperConfiguration zookeeperConfiguration;

    private JobStatisticsAPI jobStatisticsAPI;

    private ShardingStatisticsAPI shardingStatisticsAPI;

    /**
     * 注册一个Job
     *
     * @param jobPacket
     */
    @Override
    public void registerJob(JobPacketBO jobPacket) {
        if (!jobPacket.isSingleJob() || getJobInstance(jobPacket.getJobName()).isEmpty()) {
            log.info("Register a job");
            JobEventConfiguration jobEventConfiguration = jobPacket.getJobEventConfiguration();
            List<ElasticJobListener> jobListeners = jobPacket.getJobListeners();
            ElasticJobListener[] elasticJobListeners = new ElasticJobListener[jobListeners.size()];
            jobPacket.getJobListeners().toArray(elasticJobListeners);
            JobScheduler jobScheduler;
            if (jobEventConfiguration != null) {
                jobScheduler = new JobScheduler(registryCenter, createJobConfiguration(jobPacket), jobEventConfiguration, elasticJobListeners);
            } else {
                jobScheduler = new JobScheduler(registryCenter, createJobConfiguration(jobPacket), elasticJobListeners);
            }
            jobScheduler.init();
        }
    }

    /**
     * 注册单个Job，若Job已经存在，则不会添加
     *
     * @param jobPacket
     */
    @Override
    public void registerSingleJob(JobPacketBO jobPacket) {
        log.info("Register a single job");
        jobPacket.setSingleJob(true);
        registerJob(jobPacket);
    }

    @Override
    public List<String> getJobInstance(String jobName) {
        Assert.notNull(jobName, "JobName can not be empty");
        JobNodePath jobNodePath = new JobNodePath(jobName);
        String instancesNodePath = jobNodePath.getInstancesNodePath();
        return registryCenter.getChildrenKeys(instancesNodePath);
    }

    @Override
    public void shutdown(String jobName) {
        Assert.notNull(jobName, "JobName can not be empty");
        JobNodePath jobNodePath = new JobNodePath(jobName);
        for (String each : registryCenter.getChildrenKeys(jobNodePath.getInstancesNodePath())) {
            registryCenter.remove(jobNodePath.getInstanceNodePath(each));
        }
    }

    @Override
    public void remove(String jobName) {
        shutdown(jobName);
        JobNodePath jobNodePath = new JobNodePath(jobName);
        List<String> servers = registryCenter.getChildrenKeys(jobNodePath.getServerNodePath());
        for (String each : servers) {
            registryCenter.remove(jobNodePath.getServerNodePath(each));
        }
        registryCenter.remove("/" + jobName);
    }


    @Override
    public void update(JobPacketBO jobPacket) {
        JobNodePath jobNodePath = new JobNodePath(jobPacket.getJobName());
        registryCenter.update(jobNodePath.getConfigNodePath(), LiteJobConfigurationGsonFactory.toJsonForObject(jobPacket));
    }

    @Override
    public JobBriefInfo getJobBriefInfo(String jobName) {
        return getJobStatisticsAPI().getJobBriefInfo(jobName);
    }


    @Override
    public Collection<ShardingInfo> getShareInfo(String jobName) {
        return getShardingStatisticsAPI().getShardingInfo(jobName);
    }

    private JobStatisticsAPI getJobStatisticsAPI() {
        if (jobStatisticsAPI == null) {
            jobStatisticsAPI = JobAPIFactory.createJobStatisticsAPI(zookeeperConfiguration.getServerLists(), zookeeperConfiguration.getNamespace(),
                    Optional.fromNullable(zookeeperConfiguration.getDigest()));
        }
        return jobStatisticsAPI;
    }

    private ShardingStatisticsAPI getShardingStatisticsAPI() {
        if (shardingStatisticsAPI == null) {
            shardingStatisticsAPI = JobAPIFactory.createShardingStatisticsAPI(zookeeperConfiguration.getServerLists(), zookeeperConfiguration.getNamespace(),
                    Optional.fromNullable(zookeeperConfiguration.getDigest()));
        }
        return shardingStatisticsAPI;
    }


    /**
     * 创建任务配置
     *
     * @param jobPacket
     * @return
     */
    private static LiteJobConfiguration createJobConfiguration(JobPacketBO jobPacket) {
        // 定义作业核心配置
        JobCoreConfiguration.Builder builder = JobCoreConfiguration.newBuilder(jobPacket.getJobName(), jobPacket.getCron(), jobPacket.getShardingTotalCount());
        //处理其他非必要的属性
        builder.shardingItemParameters(jobPacket.getShardingItemParameters());
        builder.jobParameter(jobPacket.getJobParameter());
        builder.failover(jobPacket.isFailover());
        builder.misfire(jobPacket.isMisfire());
        builder.description(jobPacket.getDescription());
        jobPacket.getJobProperties().forEach(builder::jobProperties);

        JobCoreConfiguration simpleCoreConfig = builder.build();
        JobTypeConfiguration jobTypeConfiguration = null;
        Class elasticJobClass = jobPacket.getElasticJobClass();
        try {
            Object instance = elasticJobClass.newInstance();
            if (instance instanceof SimpleJob) { // 定义Simple类型配置
                jobTypeConfiguration = new SimpleJobConfiguration(simpleCoreConfig, elasticJobClass.getCanonicalName());
            } else {// 定义Dataflow类型配置
                jobTypeConfiguration = new DataflowJobConfiguration(simpleCoreConfig, elasticJobClass.getCanonicalName(), jobPacket.isStreamingProcess());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(jobTypeConfiguration).build();
        return simpleJobRootConfig;
    }
}
