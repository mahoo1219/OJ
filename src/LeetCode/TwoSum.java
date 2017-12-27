package LeetCode;

import java.util.HashMap;

/**
 * 1. Two Sum
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class TwoSum {
    //数组不有序
    public int[] twoSum(int[] nums, int target) { // O(n^2)的竟然AC，我去。
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); // 数值，下标
        int sub;
        for (int i = 0; i < nums.length; i++) {
            sub = target - nums[i];
            if (map.containsKey(sub))
                return new int[]{i, map.get(sub)};
            else map.put(nums[i], i);
        }
        return null;
    }

    public int[] twoSum3(int[] nums, int target) {
        int numMin = Integer.MAX_VALUE;
        int numMax = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num < numMin) {
                numMin = num;
            }

            if (num > numMax) {
                numMax = num;
            }
        }

        int max = target - numMin;
        int min = target - numMax;

        int targetMax = max > numMax ? numMax : max;
        int targetMin = min < numMin ? numMin : min;

        int[] numIndices = new int[targetMax - targetMin + 1]; // 用数组替换map,来保存所有可能的和值

        for (int i = 0; i <= numIndices.length - 1; i++) {
            numIndices[i] = -1;
        }

        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] >= targetMin && nums[i] <= targetMax) {
                int offset = -targetMin; //定位到数组中
                if (numIndices[(target - nums[i]) + offset] != -1) {
                    return new int[]{numIndices[(target - nums[i]) + offset], i};
                } else {
                    numIndices[nums[i] + offset] = i;
                }
            }
        }
        return new int[]{0, 0};
    }
}
