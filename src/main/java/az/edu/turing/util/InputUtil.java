package az.edu.turing.util;

import java.util.Scanner;

public class InputUtil {

    public String getString(String title) {
        Scanner sc = new Scanner(System.in);
        System.out.println(title + ":");
        return sc.nextLine();
    }

    public Integer getInteger(String title) {
        Scanner sc = new Scanner(System.in);
        System.out.println(title + ":");
        return sc.nextInt();
    }
}
