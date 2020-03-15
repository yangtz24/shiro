package com.ytz.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@SpringBootTest
 public class MailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 简单邮件发送
     */
    @Test
    public void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("这是测试-林节的邮件主题");
        mailMessage.setText("这是测试邮件内容");
        mailMessage.setFrom("1506163788@qq.com");
        mailMessage.setSentDate(new Date());
        mailMessage.setTo("1026343176@qq.com");
        javaMailSender.send(mailMessage);
    }

    /**
     * 带附件发送
     * @throws MessagingException
     */
    @Test
    public void sendMultipart() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setSubject("这是测试-林节的邮件主题(带附件)");
        messageHelper.setText("Yes, you aer right!!! I am coding.......");
        messageHelper.setFrom("1506163788@qq.com");
        messageHelper.setSentDate(new Date());
        messageHelper.setTo("1026343176@qq.com");
        messageHelper.addAttachment("img.jpg", new File("C:\\Users\\robu\\Documents\\Tencent Files\\1506163788\\FileRecv\\MobileFile\\img.jpg"));
        javaMailSender.send(mimeMessage);

    }

    /**
     * 正文中带图片
     * @throws MessagingException
     */
    @Test
    public void sendImg() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setSubject("这是测试-林节的邮件主题(带图片)");
        messageHelper.setText("第一张图片：<img src='cid:p01'/>, 这是第二张图片：<img src='cid:p02'/>", true);
        messageHelper.setFrom("1506163788@qq.com");
        messageHelper.setSentDate(new Date());
        messageHelper.setTo("1026343176@qq.com");
        messageHelper.addInline("p01", new FileSystemResource(new File("C:\\Users\\robu\\Documents\\Tencent Files\\1506163788\\FileRecv\\MobileFile\\img.jpg")));
        messageHelper.addInline("p02", new FileSystemResource(new File("C:\\Users\\robu\\Documents\\Tencent Files\\1506163788\\FileRecv\\MobileFile\\dy.jpg")));
        javaMailSender.send(mimeMessage);
    }
}
