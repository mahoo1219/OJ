package LeetCode;

/**
 * 680.Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        char c, t;
        while (i <= j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        if (i > j) return true;

        return (isPalin(s, i + 1, j) || isPalin(s, i, j - 1));
    }

    public boolean isPalin(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean validPalindrome2(String s) {
        int l = -1, r = s.length();
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return isPalindromic(s, l, r + 1) || isPalindromic(s, l - 1, r);
        return true;
    }

    public boolean isPalindromic(String s, int l, int r) {
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }
}
