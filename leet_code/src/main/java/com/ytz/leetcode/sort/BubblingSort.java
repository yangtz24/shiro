package com.ytz.leetcode.sort;

import java.util.Arrays;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/28 15:04
 * @Description 冒泡排序  相邻之间比较
 */
public class BubblingSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,2,9,4,6,1};
        bubblingSort(arr);
    }

    public static void bubblingSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] > arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
