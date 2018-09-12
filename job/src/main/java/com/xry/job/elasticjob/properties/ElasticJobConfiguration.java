package com.xry.job.elasticjob.properties;


/**
 * @author jiafu
 */
//@Configuration
public class ElasticJobConfiguration {

//    @Autowired
//    private ZookeeperConfig zookeeperConfig;
//
//    /**
//     * Zookeeper连接配置
//     *
//     * @return
//     */
//    @Bean("zookeeperConfiguration")
//    @ConditionalOnMissingBean
//    public ZookeeperConfiguration zookeeperConfiguration() {
//        ZookeeperConfiguration configuration = new ZookeeperConfiguration(zookeeperConfig.getServerList(), zookeeperConfig.getNamespace());
//        configuration.setBaseSleepTimeMilliseconds(zookeeperConfig.getBaseSleepTimeMilliseconds());
//        configuration.setMaxSleepTimeMilliseconds(zookeeperConfig.getMaxSleepTimeMilliseconds());
//        configuration.setMaxRetries(zookeeperConfig.getMaxRetries());
//        configuration.setSessionTimeoutMilliseconds(zookeeperConfig.getSessionTimeoutMilliseconds());
//        configuration.setConnectionTimeoutMilliseconds(zookeeperConfig.getConnectionTimeoutMilliseconds());
//        configuration.setDigest(zookeeperConfig.getDigest());
//        return configuration;
//    }
//
//    /**
//     * 注册中心配置
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    @DependsOn("zookeeperConfiguration")
//    public CoordinatorRegistryCenter registryCenter() {
//        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration());
//        regCenter.init();
//        return regCenter;
//    }

}
