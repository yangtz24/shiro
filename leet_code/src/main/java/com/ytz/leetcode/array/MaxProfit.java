package com.ytz.leetcode.array;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/15 17:57
 * @Description
 */
public class MaxProfit {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int result = 0;

        // 双循环 超时
        // for (int i = 0; i < len - 1; i++) {
        //     for (int j = i + 1 ; j < len; j++) {
        //         if (prices[j] - prices[i] > result) {
        //             result = prices[j] - prices[i];
        //         }
        //     }
        // }

        // 双指针
        int left = 0, right = 1;
        while(right < len) {
            if (prices[right] < prices[left]) {
                left  = right;
            } else {
                result = Math.max(result, prices[right] - prices[left]);
            }
            right ++;
        }
        return result;
    }
}
