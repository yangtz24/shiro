package com.sihai.service;

import com.sihai.pojo.User;

import java.util.List;

/**
 * @author leechenxiang
 * @version V1.0
 * @Title: UserService.java
 * @Package com.sihai.service
 * @Description: 用户处理的的相关操作 service
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * @date 2017年8月17日 下午8:11:54
 */
public interface UserService {

    /**
     * @param user
     * @Description: 新增用户
     * @author leechenxiang
     * @date 2017年8月17日 下午8:13:11
     */
    public void saveUser(User user);

    /**
     * @param user
     * @Description: 更新用户
     * @author leechenxiang
     * @date 2017年8月17日 下午8:13:21
     */
    public void updateUserById(User user);

    /**
     * @param userId
     * @Description: 删除用户
     * @author leechenxiang
     * @date 2017年8月17日 下午8:13:28
     */
    public void deleteUserById(String userId);

    /**
     * @param userId
     * @return
     * @Description: 根据用户主键ID获取用户信息
     * @author leechenxiang
     * @date 2017年8月17日 下午8:22:08
     */
    public User getUserById(String userId);

    /**
     * @return
     * @Description: 获取用户列表
     * @author leechenxiang
     * @date 2017年8月17日 下午8:13:38
     */
    public List<User> getUserList();
}
