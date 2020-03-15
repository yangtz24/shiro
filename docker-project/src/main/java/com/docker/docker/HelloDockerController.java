package com.docker.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description HelloDockerController
 * @date 2019/11/22 9:56
 **/
@RestController
public class HelloDockerController {

    @GetMapping("hello")
    public String hello() {
        return "hello docker";
    }
}
