package com.rabbitmq.controller;

import com.rabbitmq.bean.Mail;
import com.rabbitmq.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/4 9:46
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private ProduceService produceService;

    @PostMapping("send")
    public boolean sendMail(Mail mail) {
        return produceService.send(mail);
    }
}
