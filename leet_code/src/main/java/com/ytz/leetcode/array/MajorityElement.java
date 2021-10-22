package com.ytz.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Nancal.com Inc.
 * Copyright (c) 2021- All Rights Reserved.
 *
 * @Author yangtz
 * @Date 2021/10/22 10:17
 * @Description
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,2,3,3,3,3,3,3};
        List<Integer> integers = majorityElement(arr);
        integers.forEach(System.out::println);
    }

    public static List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(8);

        int n = nums.length;
        int count = n / 3;
        for (int num : nums) {
            if (map.containsKey(num)) {
                Integer c = map.get(num);
                ++c;
                map.put(num, c);
            } else {
                map.put(num, 1);
            }
        }
        List<Integer> collect = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > count) {
                collect.add(entry.getKey());
            }
        }

        return collect;
    }
}
