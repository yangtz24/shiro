package com.rabbitmq.task;

import com.rabbitmq.bean.MsgLog;
import com.rabbitmq.common.Constant;
import com.rabbitmq.service.MsgLogService;
import com.rabbitmq.util.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: ResendMsg
 * @Description: MQ挂掉，消息发送失败 利用定数任务对消息投递失败进行补偿
 * @author: yangtianzeng
 * @date: 2020/4/4 11:02
 */
@Component
@Slf4j
public class ResendMsg {

    /**
     * 最大投递次数  3
     */
    private static final int MAX_TRY_COUNT = 3;


    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 每 30s 执行一次
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resend() {
        log.info("开始执行定时任务(重新投递消息)");

        List<MsgLog> msgLogs = msgLogService.selectTimeoutMsg();
        msgLogs.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            // 判断重试次数是否超过最大限制次数，是：投递失败  否：继续投递
            if (MAX_TRY_COUNT <= msgLog.getTryCount()) {
                // 设置此消息的状态为 投递失败
                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_FAIL);
                log.info("超过最大重试次数, 消息投递失败, msgId: {}", msgId);
            } else {
                // 重试 投递次数 +1
                msgLogService.updateTryCount(msgId, msgLog.getNextTryTime());

                // 投递消息  重新投递
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(msgLog.getExchange(), msgLog.getRoutingKey(),
                        MessageHelper.objToMsg(msgLog.getMsg()), correlationData);

                log.info("第 " + (msgLog.getTryCount() + 1) + " 次重新投递消息");
            }
        });
        log.info("定时任务执行结束(重新投递消息)");
    }

}
