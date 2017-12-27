package LeetCode;

/**
 * 209. Minimum Size Subarray Sum
 * https://leetcode.com/articles/minimum-size-subarray-sum/
 * 最短连续子数组的长度，满足子数组的和大于一个给定的值
 */
public class MinimumSum {
    // O(n^3)
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int res = n + 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                //求和
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= s) {
                    res = Math.min(res, j - i + 1);
                    break; //Found the smallest subarray with sum>=s starting with index i, hence move to next index
                }
            }
        }
        return (res == n + 1) ? 0 : res;
    }

    // O(n^2)
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        int res = n + 1;

        // sums[i]存放nums[0...i-1]的和
        int[] sums = new int[n + 1];
        sums[0] = 0;
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 使用sums[r+1] - sums[l] 快速获得nums[l...r]的和
                if (sums[j + 1] - sums[i] >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return (res == n + 1) ? 0 : res;
    }

    // O(nlgn) binarySearch
    public int minSubArrayLen3(int s, int[] nums) {
        int n = nums.length;
        int res = n + 1;
        int[] sums = new int[n + 1];
        sums[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1]; // sums[i]存放nums[0...i-1]的和
        }

        for (int i = 0; i <= n; i++) {
            int right = lowerBound(sums, s + sums[i]); //返回的是在sums中的索引
            if(right != sums.length)
            res = Math.min(res, right - i);
        }
        return (res == n + 1) ? 0 : res;
    }

    // 在有序数组nums中寻找大于等于target的最小值
    // 如果没有（nums数组中所有值都小于target），则返回nums.length
    private int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length; // 在nums[l...r)的范围里寻找解
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    // O(n) 滑动窗口
    public int minSubArrayLen4(int s, int[] nums) {
        int n = nums.length;
        int res = n + 1;
        int sum = 0;
        int l = 0, r = -1; // nums[l...r]为我们的滑动窗口
        while (l < n) { // 窗口的左边界在数组范围内,则循环继续
            if (r + 1 < n && sum < s)
                sum += nums[++r];
            else
                sum -= nums[l++];
            if (sum >= s)
                res = Math.min(res, r - l + 1);
        }
        return (res == n + 1) ? 0 : res;
    }

    public int minSubArrayLen5(int s, int[] nums) {

        if (s <= 0 || nums == null)
            throw new IllegalArgumentException("Illigal Arguments");

        int l = 0, r = -1; // [l...r]为我们的窗口
        int sum = 0;
        int res = nums.length + 1;

        while (r + 1 < nums.length) {   // 窗口的右边界无法继续扩展了, 则循环继续
            while (r + 1 < nums.length && sum < s)
                sum += nums[++r];

            while (l < nums.length && sum >= s) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l++];
            }
        }

        return (res == nums.length + 1) ? 0 : res;
    }

    public int minSubArrayLen6(int s, int[] nums) {
        int n = nums.length;
        int ans = n + 1;
        int left = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= s) {
                ans = Math.min(ans, i + 1 - left);
                sum -= nums[left++];
            }
        }
        return (ans != n + 1) ? ans : 0;
    }


    public static void main(String[] args) {
        MinimumSum m = new MinimumSum();
        int[] a = {1, 3, 5, 7, 9};
        System.out.println(m.minSubArrayLen3(25, a));
    }
}
