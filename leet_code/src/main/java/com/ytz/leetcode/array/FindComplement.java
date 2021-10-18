package com.ytz.leetcode.array;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/18 14:12
 * @Description
 */
public class FindComplement {
    public static void main(String[] args) {
        System.out.println(findComplement(5));
    }

    public static int findComplement(int num) {
        // 找到最高位的1，左移一位并-1，异或
        int highbit = 1;
        int x = num;
        while (x != 0) {
            // 找最低位（最右边）的 1
            highbit = x & (-x);
            // 打掉最低位（最右边）的1
            x = x & (x - 1);
        }

        return num ^ ((highbit << 1) - 1);
    }
}
