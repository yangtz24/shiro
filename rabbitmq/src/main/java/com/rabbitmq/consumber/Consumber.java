package com.rabbitmq.consumber;

import com.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @description Consumber
 * @date 2019/11/28 18:20
 **/
@Component
public class Consumber {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(value = @Queue(value = RabbitmqConfig.QUEUE_RABBIT + "q"),
                    exchange = @Exchange(value = RabbitmqConfig.EXCHANGE_RABBIT, type = ExchangeTypes.TOPIC),
                    key = RabbitmqConfig.BINDING_RABBIT))
    public void receiverMsg1(String msg) {
        System.out.println(msg + "   receiverMsg1");
    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_RABBIT)
    public void receiverMsg2(String msg) {
        System.out.println(msg + "   receiverMsg2");
    }
}
