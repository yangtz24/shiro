package com.security.service;

import com.security.model.User;
import com.security.repository.RoleMapper;
import com.security.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @description UserService
 * @date 2019/11/19 11:30
 **/
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userRepository;

    @Autowired
    private RoleMapper roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRoles(roleRepository.findUserRolesByUserId(user.getId()));
        return user;
    }
}
