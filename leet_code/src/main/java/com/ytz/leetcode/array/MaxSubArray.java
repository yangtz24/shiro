package com.ytz.leetcode.array;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/13 18:28
 * @Description
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int re = nums[0];
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            re = Math.max(sum, re);
        }
        return re;
    }
}
