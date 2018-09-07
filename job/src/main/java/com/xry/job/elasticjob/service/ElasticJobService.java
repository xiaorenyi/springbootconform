package com.xry.job.elasticjob.service;

import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.lite.lifecycle.domain.ShardingInfo;
import com.xry.job.elasticjob.service.bo.JobPacketBO;

import java.util.Collection;
import java.util.List;

/**
 * @Author JH.TAO
 * @create 2017-12-28 14:33
 */

public interface ElasticJobService {

    /**
     * 注册一个Job
     *
     * @param job
     */
    void registerJob(JobPacketBO job);

    /**
     * 注册单个Job，若Job已经存在，则不会添加
     *
     * @param job
     */
    void registerSingleJob(JobPacketBO job);

    /**
     * 获取Job实例数
     *
     * @param jobName
     * @return
     */
    List<String> getJobInstance(String jobName);

    /**
     * 作业关闭
     *
     * @param jobName
     */
    void shutdown(String jobName);

    /**
     * 作业删除
     *
     * @param jobName
     */
    void remove(String jobName);

    /**
     * 作业修改
     */
    void update(JobPacketBO jobPacket);

    /**
     * 获取作业简明信息
     *
     * @param jobName
     * @return
     */
    JobBriefInfo getJobBriefInfo(String jobName);

    /**
     * 获取分片信息
     *
     * @param jobName
     * @return
     */
    Collection<ShardingInfo> getShareInfo(String jobName);
}
