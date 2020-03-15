package com.learn.controller;

import com.learn.pojo.Test;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description TestController
 * @date 2019/9/5 14:53
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String get(String name, Integer age) {
        System.out.println(name + "---------------->" + age);
        return "get";
    }

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String post(Test test) {
        System.out.println(test.getName() + "---------------->" + test.getAge());
        return "post";
    }
}
