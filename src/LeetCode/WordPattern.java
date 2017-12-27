package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 290. Word Pattern
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class WordPattern {
    public static boolean wordPattern(String pattern, String str) {
        String[] strings = str.split(" ");
        String[] patterns = new String[26]; // lowercase letters

        if (pattern.length() != strings.length)
            return false;

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (patterns[c - 'a'] == null) {
                patterns[c - 'a'] = strings[i];
            } else if (!patterns[c - 'a'].equals(strings[i]))
                return false;
        }
        // 是否有重复值，多对一映射O(26^2)
        for (int i = 0; i < patterns.length; i++) {
            for (int j = i + 1; j < patterns.length; j++) {
                if (patterns[i] != null && patterns[j] != null)
                    if (patterns[i].equals(patterns[j]))
                        return false;
            }
        }
        return true;
    }

    public boolean wordPattern2(String pattern, String str) {
        String[] arr = str.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();
        if (arr.length != pattern.length())
            return false;
        for (int i = 0; i < arr.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(arr[i]))
                    return false;
            } else {
                if (map.containsValue(arr[i])) // 这里使用containsValue()需要遍历整棵树
                    return false;
                map.put(c, arr[i]);
            }
        }
        return true;
    }

    public boolean wordPattern3(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;

        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(words[i]))
                    return false;
            } else {
                if (set.contains(words[i]))
                    return false;
                map.put(c, words[i]);
                set.add(words[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String p = "abba";
        String str = "dog cat cat dog";
        System.out.println(wordPattern(p, str));
    }
}
