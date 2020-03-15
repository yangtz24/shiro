package com.learn.controller;

import com.learn.pojo.User;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author UserController
 * @description
 * @date 2019/8/7 13:31
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "{id}/one", method = RequestMethod.GET)
    public User getOne(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

}
