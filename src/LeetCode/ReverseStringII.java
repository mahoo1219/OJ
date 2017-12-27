package LeetCode;

/**
 * 541.Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 * The string consists of lower English letters only.
 * Length of the given string and k will in the range [1, 10000]
 */
public class ReverseStringII {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0)
            return s;
        int n = s.length();
        int l = 0, r;
        char[] str = s.toCharArray();
        while (l < n) {
            r = Math.min(l + k - 1, n - 1);
            reverse(str, l, r);
            l += 2 * k;
        }
        return String.valueOf(str);
    }

    public void reverse(char[] str, int l, int r) {
        char c;
        for (int i = l, j = r; i < j; i++, j--) {
            c = str[i];
            str[i] = str[j];
            str[j] = c;
        }
    }
}
