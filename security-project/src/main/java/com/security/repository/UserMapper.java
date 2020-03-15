package com.security.repository;

import com.security.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 * @description UserRepository
 * @date 2019/11/19 11:29
 **/
@Mapper
/*public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = :username")
    User findByUserName(@Param("username") String username);
}*/
public interface UserMapper {
    User findByUserName(@Param("username") String username);
}
