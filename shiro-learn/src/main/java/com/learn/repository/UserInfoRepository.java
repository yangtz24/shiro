package com.learn.repository;

import com.learn.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @description UserInfoRepository
 * @date 2019/8/8 10:53
 **/
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /**
     * 通过username查找用户信息
     *
     * @param username
     * @return
     */
    public UserInfo findByUsername(String username);
}
