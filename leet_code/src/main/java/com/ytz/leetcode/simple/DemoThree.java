package com.ytz.leetcode.simple;

import java.util.*;

/**
 * @ClassName: DemoThree
 * @Description: TODO  13.罗马数字转整数
 * @author: yangtz
 * @date: 2020/11/24
 * @Version: V1.0
 */
public class DemoThree {

    private static final HashMap<Character, Integer> map;

    static {
        map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }


    public static void main(String[] args) {
        String str = "IX";
        int i = romanToInt(str);
        System.out.println(i);
    }

    public static int romanToInt(String s) {
        Boolean b = checkRomanString(s);
        if (!b) {
            return 0;
        }
        int result = 0;
        char[] array = s.toCharArray();
        int len = array.length;
        char c = array[0];
        Integer preNum = map.get(c);
        for (int i = 1; i < len; i++) {
            char c1 = array[i];
            if (preNum < map.get(c1)) {
                result -= preNum;
            } else {
                result += preNum;
            }

            preNum = map.get(c1);
        }
        result += preNum;
        return  result;
    }

    /**
     * 检验输入是否是罗马数字
     * @return
     */
    private static Boolean checkRomanString(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        boolean flag = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
