package com.ytz.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/13 15:56
 * @Description
 */
public class Question1 {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        if (s.startsWith(")") || s.startsWith("]") || s.startsWith("}")) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>(10000);
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        map.put('#', '#');
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        stack.add('#');
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
           if (map.containsKey(c)) {
               stack.add(c);
           } else if (map.get(stack.pop()) != c) {
               return false;
           }
        }
        return stack.size() == 1;

    }
}
