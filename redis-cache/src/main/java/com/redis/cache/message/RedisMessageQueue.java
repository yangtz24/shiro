package com.redis.cache.message;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import redis.clients.jedis.Jedis;

import javax.management.Query;
import java.util.UUID;

/**
 * @ClassName: RedisMessageQueue
 * @Description: redis 做消息队列
 * 1. 简单的实现  list
 * lpush/rpush
 * lpop/rpop
 * 实现：   在客户端（例如 Java 端），我们会维护一个死循环来不停的从队列中读取消息，并处理，如果队列中有消息，则直接获取到，
 * 如果没有消息，就会陷入死循环，
 * 直到下一次有消息进入，这种死循环会造成大量的资源浪费，这个时候，可以使用blpop/brpop 。
 * Blpop： 命令移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
 * <p>
 * 2. 延迟消息队列: zset
 * zset 中利用 score，可以把时间作为 score，将 value 存到 redis 中，然后通过轮询的方式，去不断的读取消息出来
 * @author: yangtianzeng
 * @date: 2020/3/27 9:23
 */
public class RedisMessageQueue {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        //构建一个消息队列
        DelayMsgQueue delayMsgQueue = new DelayMsgQueue(jedis, "ytzboy-queue");

        //消息生产者
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    delayMsgQueue.queue("ytzboy>>>" + i);
                }
            }
        };

        //消费者
        Thread consumer = new Thread() {
            @Override
            public void run() {
                delayMsgQueue.loop();
            }
        };

        //启动
        producer.start();
        consumer.start();

        //休息 7 秒后，停止程序
        try {
            Thread.sleep(7000);
            consumer.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
