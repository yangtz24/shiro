package com.security.repository;

import com.security.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 * @description MenuRepository
 * @date 2019/11/19 16:03
 **/

/*public interface MenuRepository extends JpaRepository<Menu, Integer> {

}*/
@Mapper
public interface MenuMapper {

    List<Menu> findAll();
}
