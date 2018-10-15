package com.xry.web.java;


import java.util.Collections;
import java.util.Random;

/**
 * 重试机制测试
 *
 * @author yuqinggen
 * @create 2018-09-11 15:29
 */
public class RetryTest {


//    private static int retryTimes = 0;
//
//
//    public static Boolean vpmsRetryCoupon(final String userId) {
//        // 构建重试模板实例
//        RetryTemplate retryTemplate = new RetryTemplate();
//        // 设置重试策略，主要设置重试次数
//        SimpleRetryPolicy policy = new SimpleRetryPolicy(3, Collections.<Class<? extends Throwable>, Boolean>singletonMap(Exception.class, true));
//        // 设置重试回退操作策略，主要设置重试间隔时间
//        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
//        fixedBackOffPolicy.setBackOffPeriod(100);
//        retryTemplate.setRetryPolicy(policy);
//        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
//        // 通过RetryCallback 重试回调实例包装正常逻辑逻辑，第一次执行和重试执行执行的都是这段逻辑
//        final RetryCallback<Object, Exception> retryCallback = new RetryCallback<Object, Exception>() {
//            //RetryContext 重试操作上下文约定，统一spring-try包装
//            public Object doWithRetry(RetryContext context) throws Exception {
//
//                boolean result = pushCouponByVpmsaa(userId);
//                System.out.println("retryCallback === retryTimes " + retryTimes);
//                if (!result) {
//                    throw new RuntimeException();//这个点特别注意，重试的根源通过Exception返回
//                }
//                return true;
//            }
//        };
//        // 通过RecoveryCallback 重试流程正常结束或者达到重试上限后的退出恢复操作实例
//        final RecoveryCallback<Object> recoveryCallback = new RecoveryCallback<Object>() {
//            public Object recover(RetryContext context) throws Exception {
//                System.out.println("recoveryCallback === retryTimes " + retryTimes);
////                logger.info("正在重试发券::::::::::::"+userId);
//                return null;
//            }
//        };
//        try {
//            System.out.println("retryTemplate.execute === retryTimes " + retryTimes);
//            // 由retryTemplate 执行execute方法开始逻辑执行
//            retryTemplate.execute(retryCallback, recoveryCallback);
//        } catch (Exception e) {
////            logger.info("发券错误异常========"+e.getMessage());
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        vpmsRetryCoupon("43333");
//    }
//
//
//    public static Boolean pushCouponByVpmsaa(String userId) {
//        Random random = new Random();
//        int a = random.nextInt(10);
//        System.out.println("a是" + a);
//        System.out.println("推送次数 retryTimes = " + ++retryTimes);
//        if (a == 8) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
