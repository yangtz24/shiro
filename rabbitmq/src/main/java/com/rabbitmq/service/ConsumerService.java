package com.rabbitmq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @ClassName: ConsumerService
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/4 9:34
 */
public interface ConsumerService {

    /**
     * 消费者 消息
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    void consume(Message message, Channel channel) throws IOException;
}
