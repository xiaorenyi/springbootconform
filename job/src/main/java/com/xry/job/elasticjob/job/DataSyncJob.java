package com.xry.job.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;

/**
 * @author yuqinggen
 * @create 2018-09-07 16:16
 */
@Slf4j
@DisallowConcurrentExecution
public class DataSyncJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {

        for (int i = 0;i< 100; i++){
            log.info("数据同步 i = {}",i);
        }

    }
}
