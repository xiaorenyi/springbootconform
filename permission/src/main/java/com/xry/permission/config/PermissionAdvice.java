package com.xry.permission.config;

import com.xry.permission.annotation.Permission;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Beldon
 * @create 2018-04-10 上午9:21
 */
public class PermissionAdvice implements MethodInterceptor {

    private final PermissionJudge permissionJudge;
    private final String userIdHeaderKey;

    public PermissionAdvice(PermissionJudge permissionJudge, String userIdHeaderKey) {
        this.permissionJudge = permissionJudge;
        this.userIdHeaderKey = userIdHeaderKey;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = request.getHeader(userIdHeaderKey);
        Permission permission = invocation.getMethod().getAnnotation(Permission.class);
        if (permissionJudge.hasPermission(userId, permission.permissions())) {
            return invocation.proceed();
        }
        return permissionJudge.noPermissionReturn();
    }
}
