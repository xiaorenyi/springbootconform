package com.xry.permission.controller;

import com.xry.permission.annotation.AopPermission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-11-13 20:41
 */
@RestController
public class AopPermissionController {

    @GetMapping("/aopPermissionAdd")
    @AopPermission(permissions = "add")
    public String permissionAdd(){
        return "aopPermissionAdd";
    }

    @GetMapping("/aopPermissionDel")
    @AopPermission(permissions = "del")
    public String permissionDel(){
        return "aopPermissionDel";
    }

    @GetMapping("/aopNoNeedPermission")
    public String noNeedPermission(){
        return "aopNoNeedPermission";
    }
}
