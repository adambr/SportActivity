package cz.muni.fi.pa165.sportactivitymanager.client;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.print("Enter something here : ");

        String sWhatever;

        Scanner scanIn = new Scanner(System.in);
        sWhatever = scanIn.nextLine();

        scanIn.close();
        System.out.println(sWhatever);
    }
}
