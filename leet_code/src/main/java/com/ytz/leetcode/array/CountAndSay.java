package com.ytz.leetcode.array;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *  给定一个正整数 n ，输出外观数列的第 n 项。
 *
 *
 *
 * @Author yangtz
 * @Date 2021/10/15 10:15
 * @Description 外观数列
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 */
public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
        System.out.println(countAndSay2(4));
    }

    /**
     *
     * @param n n
     * @return result
     */
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String str = "1";
        for (int j = 2; j <= n; j++) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                int count = 1;
                while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                    count++;
                    i++;
                }
                builder.append(count).append(str.charAt(i));
            }
            str = builder.toString();
        }
        return str;
    }

    /**
     *  递归
     * @param n n
     * @return result
     */
    public static String countAndSay2(int n) {
        if (n == 1) {
            return "1";
        }

        StringBuilder builder = new StringBuilder();
        String preString = countAndSay2(n - 1);
        int length = preString.length();
        for (int i = 0; i < length; i++) {
            int count = 1;
            while (i + 1 < length && preString.charAt(i) == preString.charAt(i + 1)) {
                count++;
                i++;
            }
            builder.append(count).append(preString.charAt(i));
        }
        return builder.toString();
    }
}
