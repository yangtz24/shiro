package com.ytz.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DemoOne
 * @Description: TODO   无重复字符的最长子串
 * @author: yangtz
 * @date: 2020/11/23
 * @Version: V1.0
 */
public class DemoOne {
    public static void main(String[] args) {
        String str = "pwwkew";
        int length = lengthOfLongestSubstring(str);
        System.out.println(length);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return  0;
        }
        char[] array = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (list.contains(array[i])) {
                String str = s.substring(0, list.size());

            } else {
                list.add(array[i]);
            }
        }
        return 0;
    }
}
