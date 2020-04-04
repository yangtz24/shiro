package com.rabbitmq.service;

import com.rabbitmq.bean.Mail;

/**
 * @ClassName: ProduceService
 * @Description: 生产者
 * @author: yangtianzeng
 * @date: 2020/4/4 9:05
 */
public interface ProduceService {

    /**
     * 发送邮件
     * @param mail
     * @return
     */
    boolean send(Mail mail);
}
