package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/articles/contains-duplicate/

/**
 * 217. Contains Duplicate
 * Given an array of integers, find if the array contains any duplicates. Your function should return true
 * if any value appears at least twice in the array, and it should return false if every element is distinct.
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.add(i))
                return true;
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        if (nums.length == 1 || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }
        if ((max - min + 1) < nums.length) {
            return true;
        }
        boolean[] results = new boolean[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] - min;
            if (results[index]) {
                return true;
            }
            results[index] = true;
        }
        return false;
    }
}
