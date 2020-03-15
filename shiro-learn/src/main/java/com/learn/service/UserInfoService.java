package com.learn.service;

import com.learn.pojo.UserInfo;

/**
 * @author
 * @description UserInfoService
 * @date 2019/8/8 10:56
 **/
public interface UserInfoService {

    public UserInfo findByUsername(String username);
}
