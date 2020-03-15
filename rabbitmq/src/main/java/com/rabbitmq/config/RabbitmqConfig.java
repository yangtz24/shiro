package com.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @description RabbitmqConfig
 * @date 2019/9/23 14:50
 **/
@Configuration
public class RabbitmqConfig {

    public static final String EXCHANGE_RABBIT = "exchange_rabbit";
    public static final String QUEUE_RABBIT = "queue_rabbit";
    public static final String BINDING_RABBIT = "binding_rabbit";

    /**
     * 定义队列
     *
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(QUEUE_RABBIT);
    }

    /**
     * 定义交换机/路由规则
     *
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_RABBIT);
    }

    /**
     * 定义绑定
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(BINDING_RABBIT);
    }


}
