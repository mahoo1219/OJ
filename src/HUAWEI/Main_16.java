package HUAWEI;

import java.util.Scanner;

// 购物单
public class Main_16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        int count = scanner.nextInt();
        scanner.nextLine();
        good[] goods = new good[count];
        for (int i = 0; i < count; i++) {
            goods[i] = new good(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
            scanner.nextLine();
        }
    }

    static class good {
        int v;
        int p;
        int q;

        public good(int v, int p, int q) {
            this.v = v;
            this.p = p;
            this.q = q;
        }
    }
}
