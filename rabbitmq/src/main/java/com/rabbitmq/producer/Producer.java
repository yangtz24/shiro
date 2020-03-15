package com.rabbitmq.producer;

import com.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @description Producer
 * @date 2019/11/28 18:10
 **/
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_RABBIT, RabbitmqConfig.BINDING_RABBIT, msg);
    }
}
