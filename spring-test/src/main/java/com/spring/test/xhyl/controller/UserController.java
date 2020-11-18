package com.spring.test.xhyl.controller;

import com.spring.test.xhyl.AutoWired1;
import com.spring.test.xhyl.service.UserService;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/16
 * @Version: V1.0
 */
public class UserController {

    @AutoWired1
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
}
