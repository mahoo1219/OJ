package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. Number of Boomerangs
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that
 * the distance between i and j equals the distance between i and k (the order of the tuple matters).
 */
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int distence,res= 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                distence = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                map.put(distence, map.getOrDefault(distence, 0) + 1);
            }
            for (int val : map.values()) {
                res += val * (val - 1);
            }
            map.clear();
        }
        return res;
    }

    public int numberOfBoomerangs2(int[][] points) {
        int result = 0;
        HashMap<Integer, Integer> distMap = new HashMap<Integer, Integer>();
        for (int[] i : points) {
            for (int[] j : points) {
                if (i == j) continue;
                int dist = (i[0] - j[0]) * (i[0] - j[0]) + (i[1] - j[1]) * (i[1] - j[1]);
                int prevDist = distMap.containsKey(dist) ? distMap.get(dist) : 0;
                result += 2 * prevDist; // 在这里就开始计算个数了
                distMap.put(dist, prevDist + 1);
            }
            distMap.clear();
        }
        return result;
    }
}
