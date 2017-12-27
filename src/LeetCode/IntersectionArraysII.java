package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 350. Intersection of Two Arrays II
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 */
public class IntersectionArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int e : nums1)
            map1.put(e, map1.getOrDefault(e, 0) + 1);
        ArrayList<Integer> temp = new ArrayList<>();
        for (int e : nums2)
            if (map1.containsKey(e) && map1.get(e) >= 1) {
                temp.add(e);
                map1.put(e, map1.get(e) - 1);
            }
        int[] res = new int[temp.size()];
        int i = 0;
        for (int e : temp) {
            res[i++] = e;
        }
        return res;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int k = 0;
        int[] ans = new int[Math.min(nums1.length, nums2.length)];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                ans[k] = nums1[i];
                i++;
                j++;
                k++;
            } else if (nums1[i] < nums2[j]) {
                while (i + 1 < nums1.length && nums1[i] == nums1[i + 1]) i++;
                i++;
            } else {
                while (j + 1 < nums2.length && nums2[j] == nums2[j + 1]) j++;
                j++;
            }
        }

        return Arrays.copyOf(ans, k);
    }
}
