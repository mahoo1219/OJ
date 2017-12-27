package HUAWEI;

import java.util.Scanner;

public class Main_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int n = number;
        int count = 0;
        // 使用位运算，使用无符号右移，否则负数会死循环。
        while (number > 0) {
            if ((number & 1) > 0)
                count++;
            number = number >>> 1;
        }
        // 另一种方法。
        while (n != 0) {
            if (n % 2 == 1) {
                count++;
            }
            n /= 2;
        }
        System.out.println(count);
    }
}
