package com.redis.cache.lock;

/**
 * @ClassName: DistributedLock
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/3/24 15:42
 */
public interface DistributedLock {

    /**
     * 获取锁
     *
     * @return 锁标识
     */
    String acquire();

    /**
     * 释放锁
     *
     * @param indentifier
     * @return
     */
    boolean release(String indentifier);
}
