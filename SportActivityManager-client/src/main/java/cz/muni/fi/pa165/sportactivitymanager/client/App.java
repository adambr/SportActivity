package cz.muni.fi.pa165.sportactivitymanager.client;

import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dobes Jakub
 *
 */
public class App {

    public static void main(String[] args) {

        /// priprava activity objektu            
//        SportActivityDTO activity2 = new SportActivityDTO();
//
//        System.out.print("Enter activity name : ");
//        Scanner scanIn = new Scanner(System.in);
//        activity2.setName(scanIn.nextLine());
//        scanIn.close();
//
//        CaloriesTableDTO calories = new CaloriesTableDTO();
//        calories.setCalories60Kg(10);
//        calories.setCalories70Kg(20);
//        calories.setCalories80Kg(30);
//        calories.setCalories90Kg(40);
//        calories.setGender(Gender.MALE);
//
//        activity2.setCalories(calories);
        
        RESTClientActivity restclient = new RESTClientActivity();

        ///////////////////////////
        /// test get by id
        ///////////////////////////
        
        

//        SportActivityDTO activity;
//        
//        System.out.print("Enter activity id : ");
//        Scanner scanIn = new Scanner(System.in);
//        activity = restclient.getActivityByID(scanIn.nextLine());
//        scanIn.close();
        
        ///////////////////////////
        /// test find all
        ///////////////////////////
        
//        List<SportActivityDTO> list = restclient.findAllActivity();
//        for (SportActivityDTO sportActivityDTO : list) {
//            System.out.println(sportActivityDTO);
//        }
        
        ///////////////////////////
        /// test delete by id
        ///////////////////////////
//        restclient.deleteActivityByID(17);
    }
}
