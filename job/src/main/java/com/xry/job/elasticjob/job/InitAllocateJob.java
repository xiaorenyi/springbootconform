package com.xry.job.elasticjob.job;


import com.xry.job.elasticjob.constant.JobConstant;
import com.xry.job.elasticjob.properties.JobProperties;
import com.xry.job.elasticjob.service.ElasticJobService;
import com.xry.job.elasticjob.service.bo.JobPacketBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 任务分配job
 *
 */
@Component
@Slf4j
public class InitAllocateJob {
    @Autowired
    private ElasticJobService elasticJobService;

    @Autowired
    private JobProperties jobProperties;

    /**
     * 分配任务
     */
    @PostConstruct
    public void allocateJob() {
        dataSyncJob();
    }

    private void dataSyncJob() {
        log.info("注册数据同步任务开始。");
        JobPacketBO jobPacketBO = new JobPacketBO(DataSyncJob.class, jobProperties.getJobNames().getSycnData()
                , jobProperties.getJobCrones().getSycnDataCron(), JobConstant.SHARDING_TOTAL_COUNT);
        elasticJobService.registerJob(jobPacketBO);
        log.info("注册数据同步任务结束。");
    }


}
