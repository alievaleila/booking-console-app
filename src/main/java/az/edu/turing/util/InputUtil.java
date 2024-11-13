package az.edu.turing.util;

import az.edu.turing.domain.entity.FlightEntity;

import java.time.LocalDateTime;
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

    public Boolean getBoolean(Boolean title) {
        Scanner sc = new Scanner(System.in);
        System.out.println(title + ":");
        return sc.nextBoolean();
    }

    public FlightEntity getFlightEntity(String title) {
        Scanner sc = new Scanner(System.in);
        System.out.println(title + ":");

        return null;
    }

    public LocalDateTime getLocalDateTime(String title) {
        Scanner sc = new Scanner(System.in);
        System.out.println(title + ":");

        return null;
    }
}
