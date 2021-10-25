package com.ytz.leetcode.array;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/25 10:35
 * @Description 矩阵 m*n target
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
            };
        System.out.println(searchMatrix(matrix, 55));
        System.out.println("----------------------------------------------");
        System.out.println(searchMatrix1(matrix, 55));
    }

    /**
     *
     * @param matrix 数组
     * @param target 目标值
     * @return 结果
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param matrix 数组
     * @param target 目标值
     * @return 结果
     */
    public static boolean searchMatrix1(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            return binarySearch(ints, target);
        }
        return false;
    }

    /**
     * 二分查找
     * @param arrs 数组
     * @param target 目标
     * @return 结果
     */
    private static boolean binarySearch(int[] arrs, int target) {
        int left = 0, right = arrs.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == arrs[mid]) {
                return true;
            }
            if (target > arrs[mid]) {
                left = mid + 1;

            }
            if (target < arrs[mid]) {
                right = mid - 1;
            }
        }
        return false;
    }
}
