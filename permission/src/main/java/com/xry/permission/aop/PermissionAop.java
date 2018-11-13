package com.xry.permission.aop;

import com.xry.permission.annotation.AopPermission;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

        String currentMothodName = joinPoint.getSignature().getName();
        log.info("around aop. currentMothorName = {}",currentMothodName);
        boolean isAopPermissionAnnotation = false;
        Method[] mothods = joinPoint.getTarget().getClass().getMethods();
        for (Method mothod : mothods) {
            if (mothod.isAnnotationPresent(AopPermission.class) && mothod.getName().equals(currentMothodName)){
                isAopPermissionAnnotation = true;
                AopPermission aopPermission =  mothod.getAnnotation(AopPermission.class);
                if (hasPermission(aopPermission)){
                    return joinPoint.proceed();
                }
            }
        }
        //方法中没有添加AopPermission注解，默认放行
        if (!isAopPermissionAnnotation){
           return joinPoint.proceed();
        }

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return "no Permission";
    }


    private boolean hasPermission(AopPermission aopPermission){
        String [] permissions = aopPermission.permissions();
        for (String permission : permissions) {
            if (permission.equals("add")){
                log.info("has aop Permission.");
                return true;
            }
        }

        return false;
    }
}
