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
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();

        System.out.println(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        int temp = 0;
        ListNode sential = new ListNode();
        ListNode result = sential;
        while(l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + temp;

            if (sum >= 10) {
                temp = 1;
                sum -= 10;
            } else {
                temp = 0;
            }

            result.next = new ListNode(sum);
            result = result.next;

            if (l1 != null) {l1 = l1.next;}
            if (l2 != null) {l2 = l2.next;}
        }

        if(temp == 1) {
            result.next = new ListNode(temp);
        }

        return sential.next;

    }
}
