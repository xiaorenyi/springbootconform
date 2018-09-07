package com.xry.job.elasticjob.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @Date: Create in 17:18 2018/5/29
 */
@Component
@ConfigurationProperties(prefix = "job")
@Data
public class JobProperties {
    @NestedConfigurationProperty
    private JobCronProperties jobCrones;
    @NestedConfigurationProperty
    private JobNameProperties jobNames;

    private String test;
}
