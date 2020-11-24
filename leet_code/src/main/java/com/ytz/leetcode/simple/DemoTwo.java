package com.ytz.leetcode.simple;

/**
 * @ClassName: DemoTwo
 * @Description: TODO  9.回文数
 * @author: yangtz
 * @date: 2020/11/23
 * @Version: V1.0
 */
public class DemoTwo {

    public static void main(String[] args) {
        int a = 112211;
        boolean f = isPalindrome(a);
        boolean b = isPalindrome2(a);
        System.out.println(f);
        System.out.println(b);
    }

    /**
     * 解法一：转变为字符串
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = x + "";
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        if (str.equals(sb.toString())) {
            return true;
        }
        return false;
    }

    /**
     * 解法二：转变原来的数值，与原来的比较，相等为true，否则为false
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int num = x;
        int newNum = 0;

        while (num > 0) {
            newNum = newNum * 10 + num % 10;
            num = num / 10;
        }

        return newNum == x;

    }
}
