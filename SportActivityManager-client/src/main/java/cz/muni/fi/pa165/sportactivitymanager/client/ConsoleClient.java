
package cz.muni.fi.pa165.sportactivitymanager.client;

import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Michal Galo
 */
public class ConsoleClient
{    
    public static void main(String[] args)
    {
        RESTClientActivity clientActivity = new RESTClientActivity();
        RESTClientUser clientUser = new RESTClientUser();
        
        entryInfo();
        info();       
        
        Scanner console = new Scanner(System.in);
        String command;
        Boolean exit = true;       
        
        while (exit)            
        {       
            System.out.println();
            System.out.print("Enter your command: "); 
            command = console.nextLine();
            
            if (command.equals("info"))
            {
                info();                
            }                
            else if (command.equals("exit"))
            {
                exit = false;
            }
            else if (command.equals("user create"))
            {
                userCreate(console, clientUser);
            }
            else if (command.equals("user get"))
            {
                userGet(clientUser);
            }
            else if (command.equals("user update"))
            {
                userUpdate(clientUser);
            }
            else if (command.equals("user delete"))
            {
                userDelete(clientUser);
            }
            else if (command.equals("user list"))
            {
                userList(clientUser);
            }
            else if (command.equals("activity create"))
            {
                activityCreate(clientActivity);
            }
            else if (command.equals("activity get"))
            {
                activityGet(clientActivity);
            }
            else if (command.equals("activity update"))
            {
                activityUpdate(clientActivity);
            }
            else if (command.equals("activity delete"))
            {
                activityDelete(clientActivity);
            }
            else if (command.equals("activity list"))
            {
                activityList(clientActivity);
            }
            else 
            {
                System.out.println("Command NOT recognized... write \"info\"");                
            }       
        }      
    }        
    
    public static void userCreate(Scanner cons, RESTClientUser clientUser)           
    {
        //Scanner cons = new Scanner(System.in);
        UserDTO userDTO = new UserDTO();
        
//        System.out.print("Enter user id: ");
//        userDTO.setId(cons.nextLong());
        
        System.out.print("Enter user first name: ");
        userDTO.setFirstName(cons.nextLine());
        
        System.out.print("Enter user last name: ");
        userDTO.setLastName(cons.nextLine());
        
        // osetrit date
//        System.out.print("Enter user birthdate: ");
        userDTO.setBirthDay(new Date(4456798798798L));
//        userDTO.setBirthDay(console.nextLong());
        
        System.out.print("Enter user weight: ");
        userDTO.setWeight(cons.nextInt());
        
        // osetrit gender
//        System.out.print("Enter user gender: ");
        userDTO.setGender(Gender.MALE);
//        userDTO.setGender(console.nextLine());
        
        userDTO = clientUser.createUser(userDTO);
        //cons.close();
        
        System.out.println("Created user{id= " + userDTO.getId() 
                    + " ,name= " + userDTO.getFirstName() + " " + userDTO.getLastName() 
                    + " ,birthday= " + userDTO.getBirthDay().toString() 
                    + " ,weight= " + userDTO.getWeight() 
                    + " ,gender= " + userDTO.getGender());
        
        cons.nextLine();
    }
    
    public static void userGet(RESTClientUser clientUser)
    {
        Scanner cons = new Scanner(System.in);
        UserDTO userDTO = new UserDTO();
        Long id;
        
        System.out.print("Enter user id: ");
        id = cons.nextLong();
        
        userDTO = clientUser.getUserByID(id);
        
        cons.close();
        
        System.out.println("User{id= " + userDTO.getId() 
                    + " ,name= " + userDTO.getFirstName() + " " + userDTO.getLastName() 
                    + " ,birthday= " + userDTO.getBirthDay().toString() 
                    + " ,weight= " + userDTO.getWeight() 
                    + " ,gender= " + userDTO.getGender());        
    }
    
    public static void userUpdate(RESTClientUser clientUser)
    {
        Scanner cons = new Scanner(System.in);
        UserDTO userDTO = new UserDTO();
        
        System.out.print("Enter user id: ");
        userDTO.setId(cons.nextLong());
        
        System.out.print("Enter user first name: ");
        userDTO.setFirstName(cons.nextLine());
        
        System.out.print("Enter user last name: ");
        userDTO.setLastName(cons.nextLine());
        
        // osetrit date
        System.out.print("Enter user birthdate: ");
        userDTO.setBirthDay(new Date(4456798798798L));
//        userDTO.setBirthDay(console.nextLong());
        
        System.out.print("Enter user weight: ");
        userDTO.setWeight(cons.nextInt());
        
        // osetrit gender
        System.out.print("Enter user gender: ");
        userDTO.setGender(Gender.MALE);
//        userDTO.setGender(console.nextLine());
        
        clientUser.updateUserByUser(userDTO);
        cons.close();
        
        System.out.println("Updated user{id= " + userDTO.getId() 
                    + " ,name= " + userDTO.getFirstName() + " " + userDTO.getLastName() 
                    + " ,birthday= " + userDTO.getBirthDay().toString() 
                    + " ,weight= " + userDTO.getWeight() 
                    + " ,gender= " + userDTO.getGender());        
    }
    
    public static void userDelete(RESTClientUser clientUser)
    {
        Scanner cons = new Scanner(System.in);
        UserDTO userDTO = new UserDTO();
        Long id;
        
        System.out.print("Enter user id: ");
        id = cons.nextLong();
        
        userDTO = clientUser.getUserByID(id);
        clientUser.deleteUserByUser(userDTO);
        
        cons.close();
        
        System.out.println("Deleted user{id= " + userDTO.getId() 
                    + " ,name= " + userDTO.getFirstName() + " " + userDTO.getLastName() 
                    + " ,birthday= " + userDTO.getBirthDay().toString() 
                    + " ,weight= " + userDTO.getWeight() 
                    + " ,gender= " + userDTO.getGender());        
    }
    
    public static void userList(RESTClientUser clientUser)
    {
        List<UserDTO> list = new ArrayList<UserDTO>();
        list = clientUser.findAllUsers();
        
        for (UserDTO userDTO: list)
        {
            System.out.println("User{id= " + userDTO.getId() 
                    + " ,name= " + userDTO.getFirstName() + " " + userDTO.getLastName() 
                    + " ,birthday= " + userDTO.getBirthDay().toString() 
                    + " ,weight= " + userDTO.getWeight() 
                    + " ,gender= " + userDTO.getGender());           
        }        
    }
    
    public static void activityCreate(RESTClientActivity clientActivity)
    {
        Scanner cons = new Scanner(System.in);
        SportActivityDTO activityDTO = new SportActivityDTO();
        CaloriesTableDTO caloriesDTO = new CaloriesTableDTO();
                
        System.out.print("Enter activity id: ");
        activityDTO.setId(cons.nextLong());
        
        // ako sa bude zadavat id pre calories table?
        caloriesDTO.setId(activityDTO.getId());
        
        System.out.print("Enter activity name: ");
        activityDTO.setName(cons.nextLine());
        
        System.out.print("Enter activity calories for 60 Kg: ");
        caloriesDTO.setCalories60Kg(cons.nextInt());
        
        System.out.print("Enter activity calories for 70 Kg: ");
        caloriesDTO.setCalories70Kg(cons.nextInt());
        
        System.out.print("Enter activity calories for 80 Kg: ");
        caloriesDTO.setCalories80Kg(cons.nextInt());
        
        System.out.print("Enter activity calories for 90 Kg: ");
        caloriesDTO.setCalories90Kg(cons.nextInt());
        
        // osetrit caloriestable gender - ako sa to tam bude zadavat?        
        caloriesDTO.setGender(Gender.MALE);
        
        activityDTO.setCalories(caloriesDTO);
        
        clientActivity.createActivity(activityDTO);
        cons.close();
        
        System.out.println("Created activity{id= " + activityDTO.getId() 
                    + " ,name= " + activityDTO.getName() 
                    + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                    + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                    + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                    + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());        
    }    
    
    public static void activityGet(RESTClientActivity clientActivity)
    {
        Scanner cons = new Scanner(System.in);
        SportActivityDTO activityDTO = new SportActivityDTO();        
        String id;
        
        System.out.print("Enter activity id: ");
        id = cons.nextLine();
        
        activityDTO = clientActivity.getActivityByID(id);
        
        cons.close();
        
        System.out.println("Activity{id= " + activityDTO.getId() 
                    + " ,name= " + activityDTO.getName() 
                    + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                    + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                    + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                    + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());
    }
    
    public static void activityUpdate(RESTClientActivity clientActivity)
    {
        Scanner cons = new Scanner(System.in);
        SportActivityDTO activityDTO = new SportActivityDTO();
        CaloriesTableDTO caloriesDTO = new CaloriesTableDTO();
                
        System.out.print("Enter activity id: ");
        activityDTO.setId(cons.nextLong());
        
        // ako sa bude zadavat id pre calories table?
        caloriesDTO.setId(activityDTO.getId());
        
        System.out.print("Enter activity name: ");
        activityDTO.setName(cons.nextLine());
        
        System.out.print("Enter activity calories for 60 Kg: ");
        caloriesDTO.setCalories60Kg(cons.nextInt());
        
        System.out.print("Enter activity calories for 70 Kg: ");
        caloriesDTO.setCalories70Kg(cons.nextInt());
        
        System.out.print("Enter activity calories for 80 Kg: ");
        caloriesDTO.setCalories80Kg(cons.nextInt());
        
        System.out.print("Enter activity calories for 90 Kg: ");
        caloriesDTO.setCalories90Kg(cons.nextInt());
        
        // osetrit caloriestable gender - ako sa to tam bude zadavat?        
        caloriesDTO.setGender(Gender.MALE);
        
        activityDTO.setCalories(caloriesDTO);
        
        clientActivity.updateActivityByActivity(activityDTO);
        cons.close();
        
        System.out.println("Updated activity{id= " + activityDTO.getId() 
                    + " ,name= " + activityDTO.getName() 
                    + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                    + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                    + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                    + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());
    }
    
    public static void activityDelete(RESTClientActivity clientActivity)
    {
        Scanner cons = new Scanner(System.in);
        SportActivityDTO activityDTO = new SportActivityDTO();        
        String id;       
                
        System.out.print("Enter activity id: ");
        id = cons.nextLine();     
        
        activityDTO = clientActivity.getActivityByID(id);        
        clientActivity.deleteActivityByActivity(activityDTO);
        
        cons.close();
        
        System.out.println("Deleted activity{id= " + activityDTO.getId() 
                    + " ,name= " + activityDTO.getName() 
                    + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                    + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                    + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                    + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());       
    } 
    
    public static void activityList(RESTClientActivity clientActivity)
    {
        List<SportActivityDTO> list = new ArrayList<SportActivityDTO>();
        list = clientActivity.findAllActivity();      
        
        for (SportActivityDTO activityDTO: list)
        {
            System.out.println("Activity{id= " + activityDTO.getId() 
                    + " ,name= " + activityDTO.getName() 
                    + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                    + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                    + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                    + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());           
        }        
    }
    
    public static void entryInfo()
    {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("--------- Welcome to REST client for Sport Manager application ---------");
        System.out.println("------------------------------------------------------------------------");          
    }  
    
    public static void info()
    {        
        System.out.println("------------------------------    INFO    ------------------------------");        
        System.out.println("entities : activity  / user ");       
        System.out.println("commands : create / get / update / delete / list");
        System.out.println("client commands: info / cancel / exit \n");        
        
        System.out.println("Example: activity create / user list"); 
        System.out.println("------------------------------------------------------------------------\n");
    }   
}
