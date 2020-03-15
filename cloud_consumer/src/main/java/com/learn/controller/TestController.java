package com.learn.controller;

import com.learn.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author TestController
 * @description
 * @date 2019/8/6 16:15
 **/
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    @RequestMapping(value = "one/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable Integer id) {
        return restTemplate.getForObject("http://CLOUD-PROVIDER-USER/user/" + id + "/one", User.class);
    }

    @GetMapping("/list")
    public List<User> getAll() {
        return restTemplate.getForObject("http://cloud-provider-user/user/", List.class);
    }
}
