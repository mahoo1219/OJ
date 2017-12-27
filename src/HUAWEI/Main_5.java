package HUAWEI;

import java.util.Scanner;

// 因为我们都忽略了超大数据，不论int,long，long long都会越界，所以题意中，才要我们输出十进制数的字符串表达。
// 需要我们实现十进制整数  字符串  的加法，这才是重点和难点！！！
public class Main_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            deal(string);
            //String str=sc.next().substring(2);
            //System.out.println(Integer.parseInt(str,16)); //Integer具有自带的函数parseInt(String， radix)进行进制转换。
        }
    }

    private static void deal(String string) {
        string = string.substring(2).toUpperCase();
        int number = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            char c = string.charAt(i);
            int digit;
            if ((c >= 'A' && c <= 'Z')) {
                digit = c - 'A' + 10;
            } else if (c <= 'A') {
                digit = Integer.parseInt("" + c);
            } else {
                throw new IllegalArgumentException();
            }
            number += digit * Math.pow(16, string.length() - i - 1);
        }
        System.out.println(number);
    }
}
