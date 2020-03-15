package com.learn.service;

import com.learn.pojo.User;

import java.util.List;

/**
 * @author UserService
 * @description
 * @date 2019/8/7 11:47
 **/
public interface UserService {

    User getById(Integer id);

    List<User> getAll();
}
