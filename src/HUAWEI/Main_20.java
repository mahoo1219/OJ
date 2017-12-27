package HUAWEI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String string = scanner.nextLine();
            if (deal(string))
                System.out.println("OK");
            else System.out.println("NG");
        }
    }

    public static boolean deal(String string) {
        if (string.length() <= 8)
            return false;
        int[] count = new int[4];
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= 48 && c <= 57)// Number
                count[0] = 1;
            else if (c >= 65 && c <= 90)// Upper Alpha
                count[1] = 1;
            else if (c >= 97 && c <= 122)// Lower Alpha
                count[2] = 1;
            else count[3] = 1;// Other Alpha
        }
        if (count[0] + count[1] + count[2] + count[3] < 3)
            return false;
        for (int i = 0; i < string.length() - 3; i++) {
            for (int j = i + 1; j < string.length() - 2; j++) {
                if (string.charAt(i) == string.charAt(j) && string.charAt(i + 1) == string.charAt(j + 1) && string.charAt(i + 2) == string.charAt(j + 2))
                    return false;
            }
        }
        return true;
    }


    public static boolean lengthCheck(String str) {//验证是否满足条件I
        return str.length() > 8;
    }


    public static boolean contentCheck(String str) {//验证是否满足条件II
        String[] regex = {"[a-z]", "[A-Z]", "\\d", "[^a-zA-Z0-9]"};
        int count = 0;
        for (int i = 0; i < 4; i++) {
            Pattern p = Pattern.compile(regex[i]);
            Matcher m = p.matcher(str);
            if (m.find()) count++;
        }
        return count >= 3 ? true : false;
    }


    public static boolean substrCheck(String str) {//验证是否满足条件III
        return !str.matches(".*(...)(.*\\1).*");
    }
}
