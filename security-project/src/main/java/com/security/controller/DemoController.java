package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description DemoController
 * @date 2019/11/19 13:49
 **/
@RestController
@RequestMapping("demo")
public class DemoController {


    @GetMapping("hello")
    public String hello() {
        return "hello Security";
    }

    @GetMapping("dba/hello")
    public String dba() {
        return "Hello DBA";
    }

    @GetMapping("admin/hello")
    public String admin() {
        return "Hello ADMIN";
    }

    @GetMapping("user/hello")
    public String user() {
        return "Hello USER";
    }
}
