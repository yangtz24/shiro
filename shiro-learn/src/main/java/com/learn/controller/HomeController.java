package com.learn.controller;

import com.learn.pojo.UserInfo;
import com.learn.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author
 * @description HomeController
 * @date 2019/8/8 9:58
 **/
@Controller
//@RequestMapping("home")
public class HomeController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

   /* @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {

        //登录失败从request中获取 shiro 处理异常信息，shiroLoginFailure:shiro异常类的全类名
        String lofinFailure = (String) request.getAttribute("shiroLofinFailure");

        String msg = "";

        if(lofinFailure != null) {
            if (UnknownAccountException.class.getName().equals(lofinFailure)) {
                System.out.println("UnknownAccountException -->帐号不存在：");
                msg = "UnknownAccountException -->帐号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(lofinFailure)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(lofinFailure)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> " + lofinFailure;
                System.out.println("else -- >" + lofinFailure);
            }
        }
        model.addAttribute("msg", msg);
        return "login";
    }*/

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            throw new RuntimeException("Account Error");
        } catch (IncorrectCredentialsException e) {
            throw new RuntimeException("密码错误");
        }

        UserInfo userInfo = userInfoService.findByUsername(username);
        subject.getSession().setAttribute("user", userInfo);
        return "SUCCESS";
    }
}
