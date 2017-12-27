package LeetCode;

/**
 * 125.Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null)
            return true;
        int i = 0, j = s.length() - 1;
        char cS, cL;
        while (i <= j) {
            cS = s.charAt(i);
            cL = s.charAt(j);
            if (!Character.isLetterOrDigit(cS)) i++;
            else if (!Character.isLetterOrDigit(cL)) j--;
            else {
                if (Character.toLowerCase(cS) != Character.toLowerCase(cL))
                    return false;
                i++;
                j--;
            }
        }
        return true;
    }

    public boolean isPalindrome2(String
        s){
            String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            String rev = new StringBuffer(actual).reverse().toString();
            return actual.equals(rev);
    }

    public static void main(String[] args) {
        String s = ".a";
        ValidPalindrome v = new ValidPalindrome();
        System.out.println(v.isPalindrome(s));
    }
}
