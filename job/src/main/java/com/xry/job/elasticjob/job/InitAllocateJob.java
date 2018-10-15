package com.xry.job.elasticjob.job;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 任务分配job
 */
@Component
@Slf4j
public class InitAllocateJob {
//    @Autowired
//    private ElasticJobService elasticJobService;
//
//    @Autowired
//    private JobProperties jobProperties;
//
//    /**
//     * 分配任务
//     */
//    @PostConstruct
//    public void allocateJob() {
//        dataSyncJob();
//    }
//
//    private void dataSyncJob() {
//        log.info("注册数据同步任务开始。");
//        JobPacketBO jobPacketBO = new JobPacketBO(DataSyncJob.class, jobProperties.getJobNames().getSycnData()
//                , jobProperties.getJobCrones().getSycnDataCron(), JobConstant.SHARDING_TOTAL_COUNT);
//        elasticJobService.registerJob(jobPacketBO);
//        log.info("注册数据同步任务结束。");
//    }


}
