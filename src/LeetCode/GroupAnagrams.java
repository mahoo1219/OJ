package LeetCode;

import java.util.*;
// https://leetcode.com/articles/group-anagrams/

/**
 * 49. Group Anagrams
 * Given an array of strings, group anagrams together.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0)
            return res;

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] key = new char[26];
            for (char c : s.toCharArray()) {
                key[c - 'a']++;
            }
            String tempS = String.valueOf(key);
            if (!map.containsKey(tempS))
                map.put(tempS, new ArrayList<>());
            map.get(tempS).add(s);
        }

        //for (List list : map.values()) { // Time Limited
        //    res.add(list);
        //}
        res.addAll(map.values());
        return res;
    }

    // 使用素数来产生唯一键值标记每个由相同字母构成的字符串
    public static List<List<String>> groupAnagrams2(String[] strs) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z

        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray()) {
                key *= prime[c - 'a'];
            }
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }

    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> res = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();

        for (String str : strs) {
            char[] key = new char[26];
            for (char c : str.toCharArray()) {
                key[c - 'a']++;
            }

            String keyStr = new String(key);

            if (hm.containsKey(keyStr)) {
                hm.get(keyStr).add(str);
            } else {
                ArrayList<String> tmp = new ArrayList<String>();
                tmp.add(str);
                hm.put(keyStr, tmp);
            }
        }
        res.addAll(hm.values());
        return res;
    }

    // Java 中，用#代替字母，例如aaabbc, "#3#2#1#0...#0"
    public List<List<String>> groupAnagrams4(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
