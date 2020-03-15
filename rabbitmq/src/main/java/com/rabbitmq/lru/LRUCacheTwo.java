/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:LRUCacheTwo.java
 * @Prject: com.rabbitmq.lru
 * @Package: com.rabbitmq.lru
 * @author: yangtianzeng
 * @date: 2020/1/20 11:42
 * @version: V1.0
 */
package com.rabbitmq.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: LRUCacheTwo
 * @Description:你 基于LinkHashMap实现LRU算法
 * @author: yangtianzeng
 * @date: 2020/1/20 11:42
 */
public class LRUCacheTwo extends LinkedHashMap {

    private int capacity;//容量

    public LRUCacheTwo(int capacity) {
        //将LinkedHashMap的accessOrder设为true ----->  按照访问的顺序排序
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * @param [entry]
     * @return boolean
     * @Title: removeEldestEntry
     * @Description: 默认LinkedHashMap并不会淘汰数据
     * 重写它的removeEldestEntry()方法，当数据数量达到预设上限后，淘汰数据
     **/
    @Override
    protected boolean removeEldestEntry(Map.Entry entry) {
        return super.size() >= capacity;
    }
}
