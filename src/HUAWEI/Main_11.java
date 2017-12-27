package HUAWEI;

import java.util.Scanner;

public class Main_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 这种方法用了2M 的内存。
        //int number = scanner.nextInt();
        //String value = String.valueOf(number);
        //StringBuilder stringBuilder = new StringBuilder();
        //for (int i = value.length() - 1; i >= 0; i--) {
        //    stringBuilder.append(value.charAt(i));
        //}
        //System.out.println(stringBuilder);


        //这种方法用了9K 的内存。
        /*****************************/
        String str = scanner.nextLine();
        // 题目要求输入数字，这里应该验证是不是数字。
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        System.out.println(stringBuilder.reverse());
    }
}
