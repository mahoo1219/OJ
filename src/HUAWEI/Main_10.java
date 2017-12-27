package HUAWEI;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        char[] chars = string.toCharArray();
        Set<Character> set = new HashSet<>();

        for (char c : chars) {
            if (c >= 0 && c <= 127) {
                set.add(c);
            }
        }
        System.out.println(set.size());

        //Set<String> strSet = new HashSet<>();
        //for (int i = 0; i < string.length(); i++) {
        //    if (string.charAt(i) >= 0 && string.charAt(i) <= 127) {
        //        strSet.add(String.valueOf(string.charAt(i))); //使用charAt 省去了转换成字符的步骤。
        //    }
        //}
    }
}
