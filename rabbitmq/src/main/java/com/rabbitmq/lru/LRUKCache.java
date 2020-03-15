/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:LRUKCache.java
 * @Prject: com.rabbitmq.lru
 * @Package: com.rabbitmq.lru
 * @author: yangtianzeng
 * @date: 2020/1/20 13:36
 * @version: V1.0
 */
package com.rabbitmq.lru;

/**
 * @ClassName: LRUKCache
 * @Description: LRU-K:  LRU算法优化
 * 热点数据问题 ：避免非热点数据淘汰掉热点数据
 * 原理：两个队列
 * 历史访问次数队列
 * 缓存队列
 * 当访问一个数据时，首先先在访问历史队列中累加访问次数，当历史访问记录超过K次后，
 * 才将数据缓存至缓存队列，从而避免缓存队列被污染。
 * @author: yangtianzeng
 * @date: 2020/1/20 13:36
 */
public class LRUKCache extends LRUCache {

    private int k;//进入缓存队列的评判标准
    private LRUCache historyList;//访问数据历史记录

    public LRUKCache(int cacheSize, int historyCapacity, int k) {
        super(cacheSize);
        this.k = k;
        this.historyList = new LRUCache(historyCapacity);
    }

    @Override
    public Integer get(Integer key) {

        //记录数据访问次数
        Integer count = historyList.get(key);
        count = count == null ? 0 : count;
        historyList.put(key, ++count);

        return super.get(key);
    }

    @Override
    public Integer put(Integer key, Integer value) {

        if (value == null) {
            return null;
        }

        //如果数据存在在缓存中，则直接从缓存中取
        if (super.get(key) != null) {
            return super.put(key, value);
        }

        //如果历史访问次数达到上限 ， 则加入缓存
        Integer count = historyList.get(key);
        count = count == null ? 0 : count;
        if (count >= k) {
            //移除访问记录
            historyList.remove(key);
            return super.put(key, value);
        }

        return super.put(key, value);
    }

    @Override
    public Integer remove(Integer key) {
        return super.remove(key);
    }
}
