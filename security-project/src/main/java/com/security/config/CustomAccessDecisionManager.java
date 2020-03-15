package com.security.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 进行权限认证
 *
 * @author
 * @description CustomAccessDecisionManager
 * @date 2019/11/19 15:40
 **/
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * 权限认证
     *
     * @param authentication 登录用户的凭证信息
     * @param o
     * @param collection     通过url匹配返回的权限信息
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 当前用户拥有的权限
        List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 访问路径所需要的权限
        List<String> collect = collection.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());

        for (String authority : authorities) {
            if (collect.contains(authority)) {
                return;
            }
        }
        // 第一种是实现
        /*Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (ConfigAttribute configAttribute : collection) {
            // 返回的为登录权限则直接返回进行登录操作
            if ("ROLE_login".equals(configAttribute.getAttribute())
                    && authentication instanceof UsernamePasswordAuthenticationToken) {
                return;
            }
            for (GrantedAuthority authority : authorities) {
                if (configAttribute.getAttribute().equals(authority.getAuthority())) {
                    return;
                }
            }
        }*/
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
