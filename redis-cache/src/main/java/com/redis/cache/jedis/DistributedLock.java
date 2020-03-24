package com.redis.cache.jedis;

/**
 * @ClassName: DistributedLock
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/3/24 15:39
 */
public interface DistributedLock {

    /**
     * 获取锁
     * @return 锁标识
     */
    String acquire();

    /**
     * 释放锁
     * @param identify
     * @return
     */
    boolean release(String identify);
}
