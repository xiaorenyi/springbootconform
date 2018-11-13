package com.xry.permission.config.security;

import com.xry.permission.config.PermissionAdvice;
import com.xry.permission.config.PermissionPointCutAdvisor;
import com.xry.permission.config.impl.PermissionJudgeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Beldon
 * @create 2018-04-16 下午3:37
 */
@Configuration
public class SecurityConfig {


    private static final String TOKEN_HEAD = "X-Head";

    @Bean
    public PermissionPointCutAdvisor permissionPointCutAdvisor() {
        return new PermissionPointCutAdvisor(permissionAdvice());
    }

    @Bean
    public PermissionAdvice permissionAdvice() {
        return new PermissionAdvice(permissionJudge(), TOKEN_HEAD);
    }

    @Bean
    public PermissionJudgeImpl permissionJudge() {
        return new PermissionJudgeImpl();
    }
}
