package com.ytz.leetcode.day;

import java.util.ArrayList;
import java.util.List;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *    给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *      输入: dividend = 10, divisor = 3
 *      输出: 3
 *      解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 *
 *
 * @Author yangtz
 * @Date 2021/10/12 13:36
 * @Description
 */
public class Day1012 {
    public static void main(String[] args) {
        System.out.println(divide(10, 3));
//        System.out.println(divide1(5, 3));
    }

    public static int divide1(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = true;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        List<Integer> candidates = new ArrayList<>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index) + candidates.get(index));
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }

        return rev ? -ans : ans;
    }

    /**
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return 结果
     */
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new RuntimeException("除数不能为0");
        }

        if (dividend == 0) {
            return 0;
        }

        if (dividend == divisor) {
            return 1;
        }

        // 当除数为1，直接返回被除数
        if (divisor == 1) {
            return dividend;
        }
        // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
        if (divisor == -1 && dividend == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        // 把被除数与除数调整为正数,为防止被除数Integer.MIN_VALUE转换为正数会溢出，使用long类型保存参数
        if (dividend < 0 && divisor < 0) {
            return divideLong(-(long) dividend, -(long) divisor);
        } else if (dividend < 0 || divisor < 0) {
            return -divideLong(Math.abs((long) dividend), Math.abs((long) divisor));
        } else {
            return divideLong(dividend, divisor);
        }
    }

    /**
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return 结果
     */
    public static int divideLong(long dividend, long divisor) {
        // 如果被除数小于除数，结果明显为0
        if (dividend < divisor) {
            return 0;
        }
        // 记录用了count个divisor的和
        long sum = divisor;
        // 使用了多少个divisor
        int count = 1;
        while (dividend >= sum) {
            // 每次翻倍
            sum <<= 1;
            count <<= 1;
        }

        // 此时dividend < sum
        sum >>>= 1;
        count >>>= 1;

        // 此时dividend >= sum
        // 将count个divisor从dividend消耗掉，剩下的还需要多少个divisor交由递归函数处理
        int i = divideLong(dividend - sum, divisor);
        return count + i;


    }
}
