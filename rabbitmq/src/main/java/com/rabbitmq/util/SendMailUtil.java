package com.rabbitmq.util;

import com.rabbitmq.bean.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SendMailUtil
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/4 8:59
 */
@Component
@Slf4j
public class SendMailUtil {

    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public boolean send(Mail mail) {

        // 目标 邮箱地址
        String to = mail.getTo();
        // 邮件标题
        String title = mail.getTitle();
        // 邮件正文
        String content = mail.getContent();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("邮件发送成功");
            return true;
        } catch (MailException e) {
            log.error("邮件发送失败, to: {}, title: {}", to, title, e);
            return false;
        }
    }

}
