package com.ytz.leetcode.array;

import java.util.Arrays;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/9 17:32
 * @Description
 */
public class DemoThree {
    public static void main(String[] args) {
        int[] nums = {1,3,45,67,23,8,34,43,78,98,5,9};
        System.out.println(getMaxNum(nums));
    }

    public static String getMaxNum(int[] nums) {
        int length = nums.length;
        String[] str = new String[length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = nums[i] + "";
        }

        Arrays.sort(str, (a,b) -> (b+a).compareTo((a+b)));

        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            sb.append(s);
        }

        return sb.toString();
    }
}
