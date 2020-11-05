package com.redis.cache.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: LruCacheDemo
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/4
 * @Version: V1.0
 */
public class LruCacheDemo<K, V> extends LinkedHashMap<K, V> {

    /**
     * 缓存大小
     */
    private int capacity;

    public LruCacheDemo(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }
}
