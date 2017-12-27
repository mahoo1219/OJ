package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 205. Isomorphic Strings
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * No two characters may map to the same character but a character may map to itself.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) != s.charAt(i))
                    return false;
            } else {
                if (set.contains(s.charAt(i)))
                    return false;
                map.put(c, s.charAt(i));
                set.add(s.charAt(i));
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {

        /*
        Questions
            are lengths equal

        */
        if (s.equals(t)) return true;

        /*
        int[] m = new int[512];
        for (int i = 0; i < s.length(); i++) {
            if (m[s.charAt(i)] != m[t.charAt(i)+256]) return false;
            m[s.charAt(i)] = m[t.charAt(i)+256] = i+1;
        }
        return true;
        */
        // if (s.length() != t.length()) return false;
        char[] map = new char[256];
        boolean[] used = new boolean[256];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            if (map[sc[i]] == 0) {
                if (used[tc[i]]) {
                    return false;
                }
                map[sc[i]] = tc[i];
                used[tc[i]] = true;
            } else {
                if (map[sc[i]] != tc[i]) return false;
            }
        }
        return true;
    }

    // 使用连个数组来判别是否相对应
    public boolean isIsomorphic3(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] sMap = new int[256];
        int[] tMap = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sMap[sChar] != tMap[tChar]) {
                return false;
            }
            sMap[sChar] = i + 1;
            tMap[tChar] = i + 1;
        }
        return true;
    }
}
