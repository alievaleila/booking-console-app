package az.edu.turing.util;

import az.edu.turing.domain.entity.FlightEntity;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InputUtil {

    public String getString(String title) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(title + ": ");
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public Integer getInteger(String title) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(title + ": ");
                int value = sc.nextInt();
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
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
