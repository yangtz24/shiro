package com.ytz.leetcode.sort;

import java.util.Arrays;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/28 16:31
 * @Description 插入排序  比较&交换
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,2,9,4,6,1};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int res = arr[i];
            int j;
            for (j = i; j > 0 && (res - arr[j - 1]) < 0; j--) {
                    arr[j] = arr[j - 1];
            }
            arr[j] = res;
        }
        System.out.println(Arrays.toString(arr));
    }
}
