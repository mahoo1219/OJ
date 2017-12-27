package LeetCode;

import java.util.HashSet;
import java.util.TreeMap;

/**
 * 349. Intersection of Two Arrays
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class IntersectionsArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> tempSet = new HashSet<>();
        for (int e : nums1)
            tempSet.add(e);
        HashSet<Integer> ResSet = new HashSet<>();
        for (int e : nums2) {
            if (tempSet.contains(e))
                ResSet.add(e);
        }
        int[] res = new int[ResSet.size()];
        int i = 0;
        for (int e : ResSet) {
            res[i++] = e;
        }
        return res;
    }
}
