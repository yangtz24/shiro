package com.redis.cache.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisDistributedLock
 * @Description: 分布式锁
 * 加锁操作的正确姿势为：
 * 使用setnx命令保证互斥性
 * 需要设置锁的过期时间，避免死锁
 * setnx和设置过期时间需要保持原子性，避免在设置setnx成功之后在设置过期时间客户端崩溃导致死锁
 * 加锁的Value 值为一个唯一标示。可以采用UUID作为唯一标示。加锁成功后需要把唯一标示返回给客户端来用来客户端进行解锁操作
 * 　　         解锁的正确姿势为：
 * 　　               1. 需要拿加锁成功的唯一标示要进行解锁，从而保证加锁和解锁的是同一个客户端
 * 　　               2. 解锁操作需要比较唯一标示是否相等，相等再执行删除操作。这2个操作可以采用Lua脚本方式使2个命令的原子性。
 * @author: yangtianzeng
 * @date: 2020/3/24 15:41
 */
public class RedisDistributedLock implements DistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLock.class);

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private Jedis jedis;//jedis
    private String lockKey;//分布式锁的键值
    private int expireTime = 10 * 1000;//超时时间 10s
    private int acquireTimeout = 1 * 1000;//锁等待，防止线程饥饿 1s

    /**
     * 获取指定键值的锁
     *
     * @param jedis
     * @param lockKey
     */
    public RedisDistributedLock(Jedis jedis, String lockKey) {
        this.jedis = jedis;
        this.lockKey = lockKey;
    }

    /**
     * 获取指定键值的锁,同时设置获取锁超时时间
     *
     * @param jedis          jedis Redis客户端
     * @param lockKey        锁的键值
     * @param acquireTimeout 获取锁超时时间
     */
    public RedisDistributedLock(Jedis jedis, String lockKey, int acquireTimeout) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.acquireTimeout = acquireTimeout;
    }

    /**
     * 获取指定键值的锁,同时设置获取锁超时时间和锁过期时间
     *
     * @param jedis          jedis Redis客户端
     * @param lockKey        锁的键值
     * @param acquireTimeout 获取锁超时时间
     * @param expireTime     锁失效时间
     */
    public RedisDistributedLock(Jedis jedis, String lockKey, int acquireTimeout, int expireTime) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.acquireTimeout = acquireTimeout;
        this.expireTime = expireTime;
    }

    @Override
    public String acquire() {
        try {
            //1.获取锁的超时时间，超过这个时间则放弃获取锁
            long endTime = System.currentTimeMillis() + acquireTimeout;
            //2. UUID随机生成VALUE
            String value = UUID.randomUUID().toString();
            //3.判断 当前系统时间是否小于 锁的超时时间
            while (System.currentTimeMillis() < endTime) {
                // set key value ex 5 nx
//                String result = jedis.set(lockKey, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                String result = jedis.set(lockKey, value);
                if (LOCK_SUCCESS.equals(result)) {
                    return value;
                }
                try {
                    //等待100毫秒
                    TimeUnit.MILLISECONDS.sleep(100L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            LOGGER.error("acquire lock due to error", e);
        }
        return null;
    }

    @Override
    public boolean release(String identify) {
        if (identify == null) {
            return false;
        }

        //lua脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = new Object();
        try {
            result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(identify));
            if (RELEASE_SUCCESS.equals(result)) {
                LOGGER.info("release lock success, requestToken:{}", identify);
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("release lock due to error", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        LOGGER.info("release lock failed, requestToken:{}, result:{}", identify, result);
        return false;
    }
}
