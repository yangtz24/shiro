package com.ytz.leetcode.day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 *给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
 *
 * answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
 * answer[i] == "Fizz" 如果 i 是 3 的倍数。
 * answer[i] == "Buzz" 如果 i 是 5 的倍数。
 * answer[i] == i 如果上述条件全不满足
 *
 *  输入：n = 3
 *  输出：["1","2","Fizz"]
 *
 * @Author yangtz
 * @Date 2021/10/13 13:54
 * @Description
 */
public class Day1013 {

    public static void main(String[] args) {
//        System.out.println(fizzBuzz(15));
        System.out.println(fizzBuzz1(15));
    }

    /**
     * @param n 输入
     * @return 结果
     */
    public static List<String> fizzBuzz1(int n) {
        if (!(n >= 1 && n <= 10000)) {
            return new ArrayList<>();
        }

        if (n == 1) {
            return Stream.of("1").collect(Collectors.toList());
        }

        List<String> answer = new ArrayList<>();

        int i = 1;
        while (i <= n) {
            if (i % 15 == 0) {
                answer.add(i - 1, "FizzBuzz");
            } else if (i % 3 == 0) {
                answer.add(i - 1, "Fizz");
            } else if (i % 5 == 0) {
                answer.add(i - 1, "Buzz");
            } else {
                answer.add(i - 1, i + "");
            }
            i++;
        }


        return answer;
    }

    /**
     * @param n 输入
     * @return 结果
     */
    public static List<String> fizzBuzz(int n) {
        if (!(n >= 1 && n <= 10000)) {
            return new ArrayList<>();
        }

        if (n == 1) {
            return Stream.of("1").collect(Collectors.toList());
        }

        List<String> answer = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                answer.add(i - 1, "FizzBuzz");
            } else if (i % 3 == 0) {
                answer.add(i - 1, "Fizz");
            } else if (i % 5 == 0) {
                answer.add(i - 1, "Buzz");
            } else {
                answer.add(i - 1, i + "");
            }
        }

        return answer;
    }
}
