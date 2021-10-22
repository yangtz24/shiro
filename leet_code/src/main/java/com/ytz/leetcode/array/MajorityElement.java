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
        int[] arr = new int[]{1,1,1,2,2,3,3,3};
        List<Integer> integers = majorityElement(arr);
        integers.forEach(System.out::println);
    }

    /**
     * map 解决
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement1(int[] nums) {
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

    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement(int[] nums) {
        // 创建返回值
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 初始化两个候选人candidate，和他们的计票
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;

        // 摩尔投票法，分为两个阶段：配对阶段和计数阶段
        // 配对阶段
        for (int num : nums) {
            // 投票
            if (cand1 == num) {
                count1++;
                continue;
            }
            if (cand2 == num) {
                count2++;
                continue;
            }

            // 第1个候选人配对
            if (count1 == 0) {
                cand1 = num;
                count1++;
                continue;
            }
            // 第2个候选人配对
            if (count2 == 0) {
                cand2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num) {
                count1++;
            } else if (cand2 == num) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            res.add(cand1);
        }
        if (count2 > nums.length / 3) {
            res.add(cand2);
        }

        return res;
    }

}
