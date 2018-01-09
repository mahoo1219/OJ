package LeetCode;

import java.util.*;

/**
 * 18. 4Sum
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note: The solution set must not contain duplicate quadruplets.
 */
public class _4Sum {
    //O(n^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 4) return res;
        int n = nums.length;

        Arrays.sort(nums);
        int max = nums[n - 1];
        if (4 * nums[0] > target || 4 * max < target)
            return res;

        int i, temp;
        for (i = 0; i < n; i++) {
            temp = nums[i];
            if (i > 0 && nums[i - 1] == temp) // avoid duplicate
                continue;
            if (temp + 3 * max < target) // temp is too small
                continue;
            if (4 * temp > target) // temp is too large
                break;
            if (4 * temp == target) { // temp is the boundary
                if (i + 3 < n && nums[i + 3] == temp)
                    res.add(Arrays.asList(temp, temp, temp, temp));
                break; // No more quadruplets
            }
            threeSumForFourSum(nums, target - temp, i + 1, n - 1, res, temp);
        }
        return res;

    }

    /*
     * Find all possible distinguished three numbers adding up to the target
	 * in sorted array nums[] between indices low and high. If there are,
	 * add all of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, the three numbers))
	 */
    private void threeSumForFourSum(int[] nums, int target, int low, int high, List<List<Integer>> res, int temp) {
        if (low + 1 >= high)
            return;

        int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target)
            return;

        int i, z;
        for (i = low; i < high - 1; i++) {
            z = nums[i];
            if (i > low && z == nums[i - 1]) // avoid duplicate
                continue;
            if (z + 2 * max < target) // z is too small
                continue;
            if (3 * z > target) // z is too large
                break;
            if (3 * z == target) {  // z is the boundary
                if (i + 1 < high && nums[i + 2] == z)
                    res.add(Arrays.asList(temp, z, z, z));
                break;
            }
            twoSumForFourSum(nums, target - z, i + 1, high, res, temp, z);
        }
    }

    /*
     * Find all possible distinguished two numbers adding up to the target
	 * in sorted array nums[] between indices low and high. If there are,
	 * add all of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
	 */
    public void twoSumForFourSum(int[] nums, int target, int low, int high, List<List<Integer>> res, int z1, int z2) {

        if (low >= high)
            return;

        if (2 * nums[low] > target || 2 * nums[high] < target)
            return;

        int i = low, j = high, sum, x;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                res.add(Arrays.asList(z1, z2, nums[i], nums[j]));

                x = nums[i];
                while (i < j && x == nums[i]) // avoid duplicate
                    i++;
                x = nums[j];
                while (i < j && x == nums[j]) // avoid duplicate
                    j--;
            }
            if (sum < target)
                i++;
            if (sum > target)
                j--;
        }
        return;
    }

    //O(n^3)
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break; //first candidate too large, search finished
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue; //first candidate too small
            if (i > 0 && nums[i] == nums[i - 1]) continue; //prevents duplicate result in ans list
            for (int j = i + 1; j < n - 2; j++) {
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break; //second candidate too large
                if (nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue; //second candidate too small
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; //prevents duplicate results in ans list
                int low = j + 1, high = n - 1;
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++; //skipping over duplicate on low
                        while (low < high && nums[high] == nums[high - 1]) high--;//skipping over duplicate on high
                        low++;
                        high--;
                    } else if (sum < target) low++;
                    else high--;
                }
            }
        }
        return res;
    }

    // TODO k 数和模板方法
    List<List<Integer>> kSum_Trim(int[] a, int target, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (a == null || a.length < k || k < 2) return result;
        Arrays.sort(a);
        kSum_Trim(a, target, k, 0, result, new ArrayList<>());
        return result;
    }

    void kSum_Trim(int[] a, int target, int k, int start, List<List<Integer>> result, List<Integer> path) {
        int max = a[a.length - 1];
        if (a[start] * k > target || max * k < target) return;

        if (k == 2) {                        // 2 Sum
            int left = start;
            int right = a.length - 1;
            while (left < right) {
                if (a[left] + a[right] < target) left++;
                else if (a[left] + a[right] > target) right--;
                else {
                    result.add(new ArrayList<>(path));
                    result.get(result.size() - 1).addAll(Arrays.asList(a[left], a[right]));
                    left++;
                    right--;
                    while (left < right && a[left] == a[left - 1]) left++;
                    while (left < right && a[right] == a[right + 1]) right--;
                }
            }
        } else {                        // k Sum
            for (int i = start; i < a.length - k + 1; i++) {
                if (i > start && a[i] == a[i - 1]) continue;
                if (a[i] + max * (k - 1) < target) continue;
                if (a[i] * k > target) break;
                if (a[i] * k == target) {
                    if (a[i + k - 1] == a[i]) {
                        result.add(new ArrayList<>(path));
                        List<Integer> temp = new ArrayList<>();
                        for (int x = 0; x < k; x++) temp.add(a[i]);
                        result.get(result.size() - 1).addAll(temp);    // Add result immediately.
                    }
                    break;
                }
                path.add(a[i]); //这里添加了某一个值，如果之后没有相对应的结果，在最后需要删除
                kSum_Trim(a, target - a[i], k - 1, i + 1, result, path);
                path.remove(path.size() - 1);        // Backtracking
            }
        }
    }

    // O(n^2)
    public List<List<Integer>> fourSum3(int[] num, int target) {
        Arrays.sort(num);

        // for holding visited pair sums. All pairs with the same pair sum are grouped together
        Map<Integer, List<int[]>> twoSumMap = new HashMap<>();
        Set<List<Integer>> res = new HashSet<>();  // for holding the results

        for (int i = 0; i < num.length; i++) {
            // get rid of repeated pair sums
            if (i > 1 && num[i] == num[i - 2]) continue;

            for (int j = i + 1; j < num.length; j++) {
                // get rid of repeated pair sums
                if (j > i + 2 && num[j] == num[j - 2]) continue;

                // for each pair sum, check if the pair sum that is needed to get the target has been visited.
                if (twoSumMap.containsKey(target - (num[i] + num[j]))) {
                    // if so, get all the pairs that contribute to this visited pair sum.
                    List<int[]> ls = twoSumMap.get(target - (num[i] + num[j]));

                    for (int[] pair : ls) {
                        // we have two pairs: one is indicated as (pair[0], pair[1]), the other is (i, j).
                        // we first need to check if they are overlapping with each other.
                        int m1 = Math.min(pair[0], i);  // m1 will always be the smallest index
                        int m2 = Math.min(pair[1], j);  // m2 will be one of the middle two indices
                        int m3 = Math.max(pair[0], i);  // m3 will be one of the middle two indices
                        int m4 = Math.max(pair[1], j);  // m4 will always be the largest index

                        if (m1 == m3 || m1 == m4 || m2 == m3 || m2 == m4) continue;  // two pairs are overlapping, so just ignore this case

                        res.add(Arrays.asList(num[m1], num[Math.min(m2, m3)], num[Math.max(m2, m3)], num[m4]));  // else record the result
                    }
                }

                // mark that we have visited current pair and add it to the corrsponding pair sum group.
                // here we've encoded the pair indices i and j into an integer array of length 2.
                twoSumMap.computeIfAbsent(num[i] + num[j], key -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        return new ArrayList<List<Integer>>(res);
    }
}
