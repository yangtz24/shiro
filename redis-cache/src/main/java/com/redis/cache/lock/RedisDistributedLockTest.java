package com.redis.cache.lock;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: RedisDistributedLockTest
 * @Description: 以秒杀库存数量为场景，测试下上面实现的分布式锁的效果
 * @author: yangtianzeng
 * @date: 2020/3/24 16:09
 */
public class RedisDistributedLockTest {

    private static int n = 500;

    public static void secondsKill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            RedisDistributedLock lock = null;
            String unLockIdentify = null;
            try {
                Jedis jedis = new Jedis("192.168.134.102", 6379);
                lock = new RedisDistributedLock(jedis, "test");
                unLockIdentify = lock.acquire();
                System.out.println(Thread.currentThread().getName() + "正在运行");
                secondsKill();
            } finally {
                if (lock != null) {
                    lock.release(unLockIdentify);
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }

}
