package HUAWEI;

import java.util.Scanner;

public class Main_13 {
    public static String reverse(String sen) {
        String[] sm = sen.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = sm.length - 1; i >= 0; --i) {
            res.append(sm[i] + " ");
        }
        return res.deleteCharAt(res.length()-1).toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String res = reverse(str);
        System.out.println(res);
    }
}
