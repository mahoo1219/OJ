package HUAWEI;

import java.util.Scanner;

public class Main_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            deal(str);
        }
    }

    public static void deal(String string) {
        while (string.length() >= 8) {
            System.out.println(string.substring(0, 8));
            string = string.substring(8);
        }
        if (string.length() < 8 && string.length() > 0) {
            string += "00000000";
            System.out.println(string.substring(0, 8));
        }
    }
    //while(sc.hasNext()){
    //    String s = new String(sc.nextLine());
    //    if(s.length()%8 !=0 )
    //        s = s + "00000000"; //这里提前加入末尾的字符串。（使得字符串长度为 8 的整数倍）。
    //
    //    while(s.length()>=8){
    //        System.out.println(s.substring(0, 8));
    //        s = s.substring(8);
    //    }
    //}
}
