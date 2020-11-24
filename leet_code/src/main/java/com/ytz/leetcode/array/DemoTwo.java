package com.ytz.leetcode.array;

import java.math.BigDecimal;

/**
 * @ClassName: DemoTwo
 * @Description: TODO   寻找两个正序数组的中位数
 * @author: yangtz
 * @date: 2020/11/23
 * @Version: V1.0
 */
public class DemoTwo {
    public static void main(String[] args) {

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0 && nums2.length == 0) {
            return BigDecimal.ZERO.doubleValue();
        }

        int[] newArray = sortArray(nums1, nums2);
        int length = newArray.length;
        if (length % 2 == 0) {
            int center = length / 2;
            return (newArray[center] + newArray[center + 1]) / 2;
        } else {
            return newArray[length / 2];
        }


    }

    private static int[] sortArray(int[] nums1, int[] nums2) {

        int newLength = nums1.length + nums2.length;
        int[] result = new int[newLength];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nums1[i];
        }
        for (int j = nums1.length; j < newLength; j++) {
            result[j] = nums2[j];
        }



        return result;
    }
}
