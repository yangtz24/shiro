/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:DoubleQueueNode.java
 * @Prject: com.rabbitmq.lru
 * @Package: com.rabbitmq.lru
 * @author: yangtianzeng
 * @date: 2020/1/20 10:28
 * @version: V1.0
 */
package com.rabbitmq.lru;

import lombok.Data;

/**
 * @ClassName: DoubleQueueNode
 * @Description:双向链表节点
 * @author: yangtianzeng
 * @date: 2020/1/20 10:28
 */
@Data
public class DoubleQueueNode {

    private int key;

    private int val;

    DoubleQueueNode pre;

    DoubleQueueNode next;

    public DoubleQueueNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
