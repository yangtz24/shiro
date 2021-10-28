package com.ytz.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/28 10:05
 * @Description 重新排序得到 2 的幂   46 true  10 false
 */
public class ReorderedPowerOf2 {

    static Set<Integer> set = new HashSet<>();
    static {
        for (int i = 1; i < (int)1e9+10; i *= 2) {
            set.add(i);
        }
    }


    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(256));
    }
    public static boolean reorderedPowerOf2(int n) {
        int[] cnts = new int[10];
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
        }
        int[] cur = new int[10];
        out:for (int x : set) {
            Arrays.fill(cur, 0);
            while (x != 0) {
                cur[x % 10]++;
                x /= 10;
            }
            for (int i = 0; i < 10; i++) {
                if (cur[i] != cnts[i]) {
                    continue out;
                }
            }
            return true;
        }
        return false;
    }
}
