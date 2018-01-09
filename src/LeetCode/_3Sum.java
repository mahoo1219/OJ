package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 15.3Sum
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 */
public class _3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int n = nums.length;
        if (n < 3)
            return res;

        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0)
                break;
            // skip equal elements to avoid duplicates in the answer without making a set or smth like that.
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) { // 73ms
                int first = i + 1, last = nums.length - 1, target = 0 - nums[i];
                while (first < last) {
                    if (nums[first] + nums[last] == target) {
                        res.add(Arrays.asList(nums[i], nums[first], nums[last]));
                        while (first < last && nums[first + 1] == nums[first])
                            ++first;
                        while (first < last && nums[last - 1] == nums[last])
                            --last;
                        ++first;
                        --last;
                    } else if (nums[first] + nums[last] < target) {
                        //while (first < last && nums[first] == nums[first + 1]) // This takes 95ms,why?
                        //    ++first;
                        ++first;
                    } else {
                        //while (first < last && nums[last] == nums[last - 1])
                        //    --last;
                        --last;
                    }
                }
                //while (i < n - 2 && nums[i] == nums[i + 1]) i++; // 84ms
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 2) {
            if (nums[i] > 0) break;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                if (sum <= 0) while (nums[j] == nums[++j] && j < k) ;
                if (sum >= 0) while (nums[k--] == nums[k] && j < k) ;
            }
            while (nums[i] == nums[++i] && i < nums.length - 2) ;
        }
        return result;
    }

    public List<List<Integer>> threeSum3(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
}
