/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:LRUCache.java
 * @Prject: com.rabbitmq.lru
 * @Package: com.rabbitmq.lru
 * @author: yangtianzeng
 * @date: 2020/1/20 10:12
 * @version: V1.0
 */
package com.rabbitmq.lru;

import lombok.Getter;
import lombok.Setter;
import sun.rmi.runtime.NewThreadAction;

import javax.xml.transform.Templates;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName: LRUCache
 * @Description: LRU算法实现
 * LRU算法全称是最近最少使用算法（Least Recently Use）  广泛的应用于缓存机制中
 * 一般来讲，LRU将访问数据的顺序或时间和数据本身维护在一个容器当中。当访问一个数据时：
 * 1.该数据不在容器当中，则设置该数据的优先级为最高并放入容器中。
 * 2.该数据在容器当中，则更新该数据的优先级至最高。
 * @author: yangtianzeng
 * @date: 2020/1/20 10:12
 */
@Getter
@Setter
public class LRUCache {

    private int size;//当前容量

    private int capacity;//限制大小

    private Map<Integer, DoubleQueueNode> map;//数据和节点的映射

    private DoubleQueueNode head;//头结点

    private DoubleQueueNode tail;//尾结点


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new DoubleQueueNode(0, 0);
        this.tail = new DoubleQueueNode(0, 0);
        this.head.next = tail;
    }

    //获取一个节点
    public Integer get(Integer key) {

        DoubleQueueNode node = map.get(key);
        if (node == null) {
            return null;
        }

        //在数据链表中则移动到链表头部
        moveToHead(node);

        return node.getVal();
    }

    //移动节点到链表头部
    private void moveToHead(DoubleQueueNode node) {
        //将node节点的前驱节点指向node节点的后继节点
        node.pre.next = node.next;
        node.next.pre = node.pre;
        DoubleQueueNode temp = head.next;
        head.next = node;
        node.next = temp;
        node.pre = head;
        temp.pre = node;
    }


    //加入链表中不存在的   新的节点
    public Integer put(Integer key, Integer value) {

        Integer oldValue;
        DoubleQueueNode node = map.get(key);
        if (node == null) {
            //淘汰数据
            eliminate();
            //数据不存在，插入到链表头部
            DoubleQueueNode newNode = new DoubleQueueNode(key, value);
            DoubleQueueNode temp = head.next;
            head.next = newNode;
            newNode.next = temp;
            newNode.pre = head;
            temp.pre = newNode;
            map.put(key, newNode);
            size++;
            oldValue = null;
        } else {
            //数据在链表中则移动到链表头部
            moveToHead(node);
            oldValue = node.getVal();
            node.setVal(value);
        }
        return oldValue;
    }

    //淘汰数据
    private void eliminate() {
        if (size < capacity) {
            return;
        }

        //将链表中最后一个节点删除
        DoubleQueueNode lastNode = tail.pre;
        map.remove(lastNode.getKey());
        lastNode.pre.next = tail;
        tail.pre = lastNode.pre;
        size--;
        lastNode = null;
    }

    //删除某一节点
    public Integer remove(Integer key) {
        DoubleQueueNode deleteNode = map.get(key);
        if (deleteNode == null) {
            return null;
        }

        deleteNode.pre.next = deleteNode.next;
        deleteNode.next.pre = deleteNode.pre;
        map.remove(key);
        return deleteNode.getVal();
    }


}
