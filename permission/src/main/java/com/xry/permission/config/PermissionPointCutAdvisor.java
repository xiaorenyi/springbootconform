package com.xry.permission.config;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * @author Beldon
 * @create 2018-04-10 上午9:40
 */
public class PermissionPointCutAdvisor extends AbstractPointcutAdvisor {

    private final Pointcut pointcut;
    private final Advice advice;

    public PermissionPointCutAdvisor(PermissionAdvice advice) {
        this.pointcut = new PermissionAnnotationMatchingPointcut();
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
