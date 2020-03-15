package com.learn.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 * @description UserInfoController
 * @date 2019/8/8 14:47
 **/
@Controller
@RequestMapping("userInfo")
public class UserInfoController {

    @GetMapping("list")
    @RequiresPermissions("userInfo:view")
    public String userInfoList() {
        return "userInfo";
    }

    @PostMapping("add")
    @RequiresPermissions("userInfo:add")
    public String add() {
        return "userInfoAdd";
    }

    @DeleteMapping("del")
    @RequiresPermissions("userInfo:del")
    public String del() {
        return "userInfoDel";
    }
}
