package com.xry.permission.controller;

import com.xry.permission.annotation.Permission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-11-05 19:03
 */
@RestController
public class permissionController {

    @GetMapping("/permissionAdd")
    @Permission(permissions = "add")
    public String permissionAdd(){

        return "permissionAdd";
    }

    @GetMapping("/permissionDel")
    @Permission(permissions = "del")
    public String permissionDel(){

        return "permissionDel";
    }

    @GetMapping("/noNeedPermission")
    public String noNeedPermission(){
        return "noNeedPermission";
    }
}
