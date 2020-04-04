package com.rabbitmq.service.impl;

import com.rabbitmq.bean.Mail;
import com.rabbitmq.bean.MsgLog;
import com.rabbitmq.config.RabbitConfig;
import com.rabbitmq.service.MsgLogService;
import com.rabbitmq.service.ProduceService;
import com.rabbitmq.util.MessageHelper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName: ProduceServiceImpl
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/4 9:06
 */
@Service
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgLogService msgLogService;

    @Override
    public boolean send(Mail mail) {

        // 创建 UUID
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        mail.setMsgId(msgId);
        //消息入库
        MsgLog msgLog = new MsgLog(msgId, mail, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
        msgLogService.insert(msgLog);

        // 发送消息 到MQ
        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME,
                MessageHelper.objToMsg(mail), correlationData);

        return true;

    }
}
