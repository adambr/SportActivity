package cz.muni.fi.pa165.sportactivitymanager.client;

import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Petr Jelinek
 */
public class InputValidator {

    public static String getString(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static int getInt(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.print("That's not an int number! " + message);
            sc.next();
        }
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    public static long getLong(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextLong()) {
            System.out.print("That's not a long number! " + message);
            sc.next();
        }
        long number = sc.nextLong();
        sc.nextLine();
        return number;
    }

    public static Gender getGender(Scanner sc) {
        String gender;
        while (true) {
            System.out.print("Enter gender (male/female): ");
            gender = sc.nextLine();
            if (gender.equals("male")) {
                return Gender.MALE;
            }
            if (gender.equals("female")) {
                return Gender.FEMALE;
            }
            System.out.print("Incorrect input. ");
        }
    }

    public static Date getDate(Scanner sc, String dateName) {
        String message = "Enter " + dateName + " in dd/MM/yyyy format: ";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date;

        while (true) {
            try {
                System.out.print(message);
                date = sc.nextLine();
                return sdf.parse(date);
            } catch (ParseException ex) {
                System.out.print("Incorrect input. ");
            }
        }
    }
}
