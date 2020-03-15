package com.learn.service.impl;

import com.learn.pojo.UserInfo;
import com.learn.repository.UserInfoRepository;
import com.learn.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author
 * @description UserInfoServiceImpl
 * @date 2019/8/8 10:57
 **/
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Transactional(readOnly = true)
    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }
}
