package com.ytz.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DemoOne
 * @Description: TODO   两数之和
 * @author: yangtz
 * @date: 2020/11/23
 * @Version: V1.0
 */
public class DemoOne {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] sum = twoSum(nums, target);
        assert sum != null;
        System.out.println(sum[0] + "," + sum[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            if (map.containsKey(result)) {
                return new int[]{map.get(result), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};

    }
}
