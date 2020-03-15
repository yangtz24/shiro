package com.security.repository;

import com.security.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 * @description RoleRepository
 * @date 2019/11/19 17:07
 **/
@Mapper
/*public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select * from role r where r.id in (select ur.rid from user_role ur where ur.uid = ?1)", nativeQuery = true)
    List<Role> findUserRolesByUserId(Integer id);
}*/
public interface RoleMapper {

    List<Role> findUserRolesByUserId(Integer id);
}
