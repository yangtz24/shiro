package com.redis.cache.pool;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: Pool
 * @Description: 连接池
 * @author: yangtianzeng
 * @date: 2020/3/24 15:14
 */
public interface Pool<T> {

    /**
     * 初始化连接池
     * @param maxTotal 最大连接数
     * @param maxWaitMillis 最大等待时间
     */
    public void init(int maxTotal, long maxWaitMillis);

    /**
     * 获取连接
     * @return 返回jedis对象
     */
    public Jedis getResource() throws Exception;

    /**
     * 释放连接
     */
    public void release(T t);

}
