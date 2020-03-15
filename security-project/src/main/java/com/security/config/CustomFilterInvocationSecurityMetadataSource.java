package com.security.config;

import com.security.model.Menu;
import com.security.model.Role;
import com.security.repository.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 过滤请求资源的路径
 *
 * @author
 * @description CustomFilterInvocationSecurityMetadataSource
 * @date 2019/11/19 15:40
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuMapper menuRepository;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 获取访问路径的权限
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 得到请求的URL
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 获取所有权限信息
        List<Menu> menus = menuRepository.findAll();
        for (Menu menu : menus) {
            // 判断请求的url和全部的url进行匹配，如果相同，则返回此url所需要的权限信息；否则 返回登录的url
            if (antPathMatcher.match(menu.getPattern(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        //没有匹配上的资源，都是登录访问
        //ROLE_login 表示登录即可访问
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
