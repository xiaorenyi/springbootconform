package com.xry.permission.controller;

import com.xry.permission.annotation.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqinggen
 * @create 2018-11-05 19:03
 */
@Controller
public class permissionController {

    @ResponseBody
    @GetMapping("/permissionAdd")
    @Permission(permissions = "add")
    public String permissionAdd(){

        return "permissionAdd";
    }

    @ResponseBody
    @GetMapping("/permissionDel")
    @Permission(permissions = "del")
    public String permission(){

        return "permissionDel";
    }
}
