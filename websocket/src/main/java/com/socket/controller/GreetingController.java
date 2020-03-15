package com.socket.controller;

import com.socket.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author
 * @description GreentingController
 * @date 2019/9/21 17:18
 **/
@Controller
public class GreetingController {


    /*
    方式一
    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public Message greeting(Message message) {
        return message;
    }
    */

    //方式二

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public void greeting(Message message) {
        simpMessagingTemplate.convertAndSend("/topic/greeting", message);
    }
}
