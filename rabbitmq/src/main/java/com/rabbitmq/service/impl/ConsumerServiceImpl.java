package com.rabbitmq.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.rabbitmq.bean.Mail;
import com.rabbitmq.bean.MsgLog;
import com.rabbitmq.client.Channel;
import com.rabbitmq.common.Constant;
import com.rabbitmq.config.RabbitConfig;
import com.rabbitmq.service.ConsumerService;
import com.rabbitmq.service.MsgLogService;
import com.rabbitmq.util.JsonUtil;
import com.rabbitmq.util.SendMailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName: ConsumerServiceImpl
 * @Description:  1.保证消费幂等性, 2.发送邮件, 3.更新消息状态, 手动ack
 * @author: yangtianzeng
 * @date: 2020/4/4 9:38
 */
@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private SendMailUtil sendMailUtil;
    @Autowired
    private MsgLogService msgLogService;

    @Override
    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {

        //将消息转化为对象
        String str = new String(message.getBody());
        Mail mail = JsonUtil.strToObj(str, Mail.class);
        log.info("收到消息: {}", mail.toString());

        //消费幂等性，如果出现已经消费，则不在重复发送
        String msgId = mail.getMsgId();
        MsgLog msgLog = msgLogService.selectByMsgId(msgId);
        if (ObjectUtil.isEmpty(msgLog) ||  Constant.MsgLogStatus.CONSUMED_SUCCESS.equals(msgLog.getStatus())) {
            log.info("重复消费, msgId: {}", msgId);
            return;
        }


        MessageProperties messageProperties = message.getMessageProperties();
        long deliveryTag = messageProperties.getDeliveryTag();

        boolean success = sendMailUtil.send(mail);
        if (success) {
            msgLogService.updateStatus(msgId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
            // 消费确认
            channel.basicAck(deliveryTag, false);
        } else {
            channel.basicNack(deliveryTag, false, true);
        }


    }
}
