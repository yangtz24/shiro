package com.ytz.leetcode.happy;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: HappyNumber
 * @Description: TODO 快乐数：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 *                  也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 *                  如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * @author: yangtz
 * @date: 2020/11/18
 * @Version: V1.0
 */
public class HappyNumber {

    public static void main(String[] args) {
        int num = 4;
        System.out.println(isHappy(num));
    }

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n > 1 && !set.contains(n)) {
            set.add(n);
            n = getNum(n);
            if (n == 1) {
                return true;
            }

        }
        return false;
    }

    public static int getNum(int number) {
            int sum = 0;
            while (number > 0) {
                int x = number % 10;
                number = number / 10;
                sum += x * x;
        }
            return sum;
    }
}
