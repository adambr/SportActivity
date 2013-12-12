package cz.muni.fi.pa165.sportactivitymanager.client;

import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

/// priprava activity objektu            
                        SportActivityDTO activity2 = new SportActivityDTO();                      
                        
                        System.out.print("Enter activity name : ");
                        Scanner scanIn = new Scanner(System.in);
                        activity2.setName(scanIn.nextLine());
                        scanIn.close();

                        CaloriesTableDTO calories = new CaloriesTableDTO();
                        calories.setCalories60Kg(10);
                        calories.setCalories70Kg(20);
                        calories.setCalories80Kg(30);
                        calories.setCalories90Kg(40);
                        calories.setGender(Gender.MALE);

                        activity2.setCalories(calories);
        
        RESTClientActivity restclient = new RESTClientActivity();
        restclient.createActivity(activity2);
//        System.out.print("Enter something here : ");
//
//        String sWhatever;
//
//        Scanner scanIn = new Scanner(System.in);
//        sWhatever = scanIn.nextLine();
//
//        scanIn.close();
//        System.out.println(sWhatever);
    }
}
