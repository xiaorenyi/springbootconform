package com.xry.permission.config;

/**
 * @author Beldon
 * @create 2018-04-10 下午3:08
 */
public interface PermissionJudge {
    /**
     * 判断用户是否有权限
     *
     * @param userId      用户id
     * @param permissions 方法权限
     * @return
     */
    boolean hasPermission(String userId, String[] permissions);

    /**
     * 没权限时返回的值
     *
     * @return
     */
    Object noPermissionReturn();
}
