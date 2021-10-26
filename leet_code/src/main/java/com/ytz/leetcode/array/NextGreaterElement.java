package com.ytz.leetcode.array;

import java.util.Arrays;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/26 14:37
 * @Description
 */
public class NextGreaterElement {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3,5,2,4};
        int[] nums2 = new int[]{6,5,4,3,2,1,7};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int j = 0;
            while (j < nums2.length && nums1[i] != nums2[j]) {
                j++;
            }
            while (j < nums2.length && nums1[i] >= nums2[j]) {
                j++;
            }
            arr[i] = j < nums2.length ? nums2[j] : -1;
        }
        return arr;
    }
}
