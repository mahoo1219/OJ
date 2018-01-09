package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 219. Contains Duplicate II
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;

        Map<Integer, Integer> map = new HashMap<>();
        //for (int i = 0; i < nums.length; i++) {
        //    if (map.containsKey(nums[i])) {
        //        if (i - map.get(nums[i]) <= k)
        //            return true;
        //    }
        //    map.put(nums[i], i);
        //}

        for (int i = 0; i < nums.length; ++i) {
            Integer lastIndex = map.put(nums[i], i); // put 返回值为原来的值，或者null(没有值)
            if (lastIndex != null && i - lastIndex <= k)
                return true;
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) return true;
        }
        return false;
    }
}
