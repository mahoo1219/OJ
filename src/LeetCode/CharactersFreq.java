package LeetCode;

import java.util.Map;
import java.util.TreeMap;

/**
 * 451. Sort Characters By Frequency
 * Given a string, sort it in decreasing order based on the frequency of characters.
 */
public class CharactersFreq {
    public String frequencySort(String s) {
        int[] freq = new int[128];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        //这里TreeMap 可以换成数组 int[] = new int[s.length+1];
        // 也可以换成是优先队列PriorityQueue
        TreeMap<Integer, StringBuilder> treeMap = new TreeMap<>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0)
                if (treeMap.containsKey(freq[i])) {
                    treeMap.put(freq[i], treeMap.get(freq[i]).append((char) i));
                } else {
                    StringBuilder temp = new StringBuilder();
                    temp.append((char) i);
                    treeMap.put(freq[i], temp);
                }
        }

        StringBuilder res = new StringBuilder();
        int n;
        for (Map.Entry<Integer, StringBuilder> e : treeMap.entrySet()) {
            n = e.getKey();
            StringBuilder tempStr = e.getValue();
            for (int j = 0; j < tempStr.length(); j++) {
                for (int i = 0; i < n; i++) {
                    res.append(tempStr.charAt(j));
                }
            }
        }
        return res.reverse().toString();
    }

    public String frequencySort2(String s) {
        int[] f = new int[256];
        char[] chars = s.toCharArray();
        char[] res = new char[s.length()];

        for (char c : chars)
            f[c]++;

        for (int i = 0; i < res.length; ) {
            int max = 0;
            int c = 0;

            for (int j = 0; j < f.length; j++) {  //在这里每次找最大的max
                if (f[j] > max) {
                    max = f[j];
                    c = j;
                }
            }

            f[c] = 0;
            while (max-- > 0)
                res[i++] = (char) c;
        }

        return new String(res);
    }
}
