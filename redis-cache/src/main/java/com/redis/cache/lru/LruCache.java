package com.redis.cache.lru;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: LruCache
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/4
 * @Version: V1.0
 */
public class LruCache {

    // 构建Node作为载体
    static class Node<K, V> {

        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = null;
            this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.value = null;
        }
    }


    // 构建一个双向队列
    static class DoubleLinkedList<K, V> {

        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        // 添加到头结点
        public void addHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // 删除节点
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        // 获得最后一个节点
        public Node<K, V> getLastNode() {
            return tail.prev;
        }

    }

    private int capacity;
    private Map<Integer, Node<Integer, Integer>> map;
    private DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>();
        this.doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }

        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.addHead(node);
        return node.value;

    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {

            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);

        } else {

            if (map.size() == capacity) {

                Node<Integer, Integer> lastNode = doubleLinkedList.getLastNode();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);

            }

            Node<Integer, Integer> newNode = new Node<>();
            newNode.value = value;
            newNode.key = key;
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(3);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        System.out.println(lruCache.map.keySet());

        lruCache.put(4, 4);
        System.out.println(lruCache.map.keySet());

        System.out.println("----------==============------------");

        lruCache.put(3, 3);
        System.out.println(lruCache.map.keySet());
        lruCache.put(3, 3);
        System.out.println(lruCache.map.keySet());
        lruCache.put(3, 3);
        System.out.println(lruCache.map.keySet());

        lruCache.put(5, 5);
        System.out.println(lruCache.map.keySet());

        /**
         * [1, 2, 3]
         * [2, 3, 4]
         * [2, 3, 4]
         * [2, 3, 4]
         * [2, 3, 4]
         * [3, 4, 5]
         */


    }



}
