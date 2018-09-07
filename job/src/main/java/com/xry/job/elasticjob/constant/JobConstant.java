package com.xry.job.elasticjob.constant;

/**
 * @author Beldon
 * @create 2018-05-22 下午4:17
 */
public interface JobConstant {
    /**
     * 分布式锁的key
     */
    String LOCK_KEY = "aceMarsoftCore_allocate_jobKey";
    String LOCK_VALUE = "lock";

    /**
     * 任务分配
     */
    int SHARDING_TOTAL_COUNT = 1;

    /**
     * 延迟5分钟后执行job
     */
    int JOB_DELAY_TIME = 60 * 5;

    /**
     * 锁5分钟
     */
    long LOCK_TIME_OUT = 60 * 5;
    Integer PAGE_SIZE = 50;

}
