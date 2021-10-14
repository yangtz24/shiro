package com.ytz.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/14 14:34
 * @Description
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(45));
    }

    public static int climbStairs(int n) {

        Map<Integer, Integer> map = new HashMap<>(100);

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

//        map.put(n, climbStairs(n-1) + climbStairs(n-2));
//        if (map.containsKey(n)) {
//            return map.get(n);
//        }
        return climbStairs(n-1) + climbStairs(n-2);
    }
}
