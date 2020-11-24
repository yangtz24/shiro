package com.ytz.leetcode.simple;

/**
 * @ClassName: DemoOne
 * @Description: TODO    7.整数反转
 * @author: yangtz
 * @date: 2020/11/23
 * @Version: V1.0
 */
public class DemoOne {

    public static void main(String[] args) {
        int a = -120;
        int reverse = reverse(a);
        System.out.println(reverse);
    }

    public static int reverse(int x) {
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            return 0;
        }
        String str = String.valueOf(x);
        if (str.endsWith("0")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str.startsWith("-")) {
            str = str.substring(1, str.length()) + "-";
        }
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        return Integer.valueOf(sb.toString());
    }
}
