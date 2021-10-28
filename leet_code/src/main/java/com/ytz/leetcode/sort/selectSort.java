package com.ytz.leetcode.sort;

import java.util.Arrays;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/28 14:56
 * @Description 选择排序 每次拿出最小的交换
 */
public class selectSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,2,9,4,6,1};
        selectSort(arr);
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
