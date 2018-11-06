package com.xry.permission.config.impl;

import com.xry.permission.config.PermissionJudge;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Beldon
 * @create 2018-04-16 下午3:39
 */
public class PermissionJudgeImpl implements PermissionJudge {


    @Override
    public boolean hasPermission(String userId, String[] permissions) {
        System.out.println("hasPermission");
        for (String s : permissions) {
            if (s.equals("add")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object noPermissionReturn() {
        System.out.println("noPermissionReturn");
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return "no permission";
    }

}
