package com.ytz.leetcode.linktable;

/**
 * @ClassName: DemoOne
 * @Description: TODO   两数相加
 * @author: yangtz
 * @date: 2020/11/23
 * @Version: V1.0
 */
public class DemoOne {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        return null;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
