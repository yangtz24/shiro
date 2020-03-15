package com.learn.repository;

import com.learn.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author UserRepository
 * @description
 * @date 2019/8/7 11:42
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
