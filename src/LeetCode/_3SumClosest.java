package LeetCode;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 */
public class _3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int res = 0;
        if (nums.length <= 3) {
            for (int i : nums) {
                res += i;
            }
            return res;
        }

        int n = nums.length;

        Arrays.sort(nums);
        res = nums[0] + nums[1] + nums[2];
        if (res > target)
            return res;
        for (int i = 0; i < n - 2; i++) {
            int low = i + 1, high = n - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == target)
                    return target;
                else if (sum < target) {
                    while (low < high && nums[low] == nums[low + 1])
                        low++;
                    low++;
                } else if (sum > target) {
                    while (low < high && nums[high] == nums[high - 1])
                        high--;
                    high--;
                }
                if (Math.abs(sum - target) < Math.abs(res - target))
                    res = sum;
            }
        }
        return res;
    }
}
