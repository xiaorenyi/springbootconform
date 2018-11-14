package com.xry.permission.aop;

import com.xry.permission.annotation.AopPermission;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yuqinggen
 * @create 2018-11-13 19:39
 */
@Component
@Aspect
@Slf4j
public class PermissionAop {

    //声明公共切入点
    @Pointcut(value = "execution(* com.xry.permission.controller.*.*(..))")
    private void myPointCut() {
    }

    @Around(value = "myPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取request
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        boolean isAopPermissionAnnotation = false;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //根据方法名称和参数获取方法
        Method realMethod = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //判断方法上是否有权限的注解AopPermission
        if (realMethod.isAnnotationPresent(AopPermission.class)) {
            isAopPermissionAnnotation = true;
            if (hasPermission(realMethod.getAnnotation(AopPermission.class).permissions())) {
                //有权限，允许访问，即调用joinPoint.proceed()方法
                return joinPoint.proceed();
            }
        }

        //方法中没有添加AopPermission注解，默认允许访问
        if (!isAopPermissionAnnotation) {
            return joinPoint.proceed();
        }

        //以上都没有通过，没有权限访问，默认拦截
        //获取response
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return "no Permission";
    }


    /**
     * 这里做权限判断逻辑，比如说根据cookie获取用户相关信息，查数据库（redis做缓存）判断是否有权限
     *
     * @param permissions
     * @return
     */
    private boolean hasPermission(String[] permissions) {
        for (String permission : permissions) {
            if (permission.equals("add")) {//简单模拟权限
                log.info("has aop Permission.");
                return true;
            }
        }
        return false;
    }
}
