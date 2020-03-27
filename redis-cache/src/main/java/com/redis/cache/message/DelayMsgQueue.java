package com.redis.cache.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName: DelayMsgQueue
 * @Description:  封装一个消息队列
 * @author: yangtianzeng
 * @date: 2020/3/27 9:30
 */
public class DelayMsgQueue {

    private Jedis jedis;
    private String queue;

    public DelayMsgQueue(Jedis jedis, String queue) {
        this.jedis = jedis;
        this.queue = queue;
    }

    /**
     *  消息入队
     * @param data  要发送的消息
     */
    public void queue(Object data) {

        StudentMessage message = new StudentMessage();
        message.setId(UUID.randomUUID().toString());
        message.setData(data);

        try {
            String msg = new ObjectMapper().writeValueAsString(message);
            System.out.println("msg publish:" + new Date());

            //发送消息  分数延迟5秒
            jedis.zadd(queue, System.currentTimeMillis() + 5000, msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费消息
     */
    public void loop() {
        while (!Thread.interrupted()) {
            //读取 score 在 0 到当前时间戳之间的消息
            Set<String> msgs = jedis.zrangeByScore(queue, 0, System.currentTimeMillis(), 0, 1);
            //如果为空，则休息500ms,再继续获取
            if (msgs.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }

            //如果获取到了消息，则读取消息
            String message = msgs.iterator().next();
            if (jedis.zrem(queue,message) > 0) {
                //抢到了，接下来 处理业务
                try {
                    StudentMessage msg = new ObjectMapper().readValue(message, StudentMessage.class);
                    System.out.println("receive msg:" +new Date()+ msg);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
