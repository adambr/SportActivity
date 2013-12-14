package cz.muni.fi.pa165.sportactivitymanager.client;

import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Michal Galo, Petr Jelinek
 */
public class ConsoleClient {

    public static void main(String[] args) {
        RESTClientActivity clientActivity = new RESTClientActivity();
        RESTClientUser clientUser = new RESTClientUser();

        entryInfo();
        info();

        Scanner console = new Scanner(System.in);
        String command;
        Boolean exit = true;

        while (exit) {
            System.out.println();
            System.out.print("Enter your command: ");
            command = console.nextLine();

            if (command.equals("info")) {
                info();
            } else if (command.equals("exit")) {
                exit = false;
            } else if (command.equals("user create")) {
                userCreate(console, clientUser);
            } else if (command.equals("user get")) {
                userGet(console, clientUser);
            } else if (command.equals("user delete")) {
                userDelete(console, clientUser);
            } else if (command.equals("user list")) {
                userList(clientUser);
            } else if (command.equals("activity create")) {
                activityCreate(console, clientActivity);
            } else if (command.equals("activity get")) {
                activityGet(console, clientActivity);
            } else if (command.equals("activity delete")) {
                activityDelete(console, clientActivity);
            } else if (command.equals("activity list")) {
                activityList(clientActivity);
            } else {
                System.out.println("Command NOT recognized... write \"info\"");
            }
        }
    }

    public static void userCreate(Scanner cons, RESTClientUser clientUser) {
        UserDTO userDTO = new UserDTO();

        System.out.print("Enter user first name: ");
        userDTO.setFirstName(cons.nextLine());

        System.out.print("Enter user last name: ");
        userDTO.setLastName(cons.nextLine());

        // date
        System.out.print("Enter birthdate in dd-M-yyyy format: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        try {
            String date = cons.nextLine();
            userDTO.setBirthDay(sdf.parse(date));
        } catch (ParseException ex) {
            System.out.println("input error...try it again");
            //cons.nextLine();
            return;
        }

        // weight
        System.out.print("Enter user weight: ");
        try {
            userDTO.setWeight(cons.nextInt());
        } catch (InputMismatchException ex) {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        // gender
        int gender;
        System.out.print("Enter user gender (male=1 / female=2): ");
        try {
            gender = cons.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        if (gender == 1) {
            userDTO.setGender(Gender.MALE);
        } else if (gender == 2) {
            userDTO.setGender(Gender.FEMALE);
        } else {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        userDTO = clientUser.createUser(userDTO);

        System.out.println("Created user{id= " + userDTO.getId()
                + " ,name= " + userDTO.getFirstName() + " " + userDTO.getLastName()
                + " ,birthday= " + userDTO.getBirthDay().toString()
                + " ,weight= " + userDTO.getWeight()
                + " ,gender= " + userDTO.getGender());

        cons.nextLine();
    }

    public static void userGet(Scanner cons, RESTClientUser clientUser) {
        Long id;

        System.out.print("Enter user id: ");

        try {
            id = cons.nextLong();
        } catch (InputMismatchException ex) {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        UserDTO userDTO;
        userDTO = clientUser.getUserByID(id);

        if (userDTO == null) {
            System.out.println("user not exists");
        } else {
            System.out.println("User {id=" + userDTO.getId()
                    + " ,name=" + userDTO.getFirstName() + " " + userDTO.getLastName()
                    + " ,birthday=" + userDTO.getBirthDay().toString()
                    + " ,weight=" + userDTO.getWeight()
                    + " ,gender=" + userDTO.getGender());
        }

        cons.nextLine();
    }

    public static void userDelete(Scanner cons, RESTClientUser clientUser) {
        Long id;
        System.out.print("Enter user id: ");

        try {
            id = cons.nextLong();
        } catch (InputMismatchException ex) {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        UserDTO userDTO = clientUser.getUserByID(id);

        if (userDTO == null) {
            System.out.println("user not exists");
        } else {
            clientUser.deleteUserByUser(userDTO);
        }
        cons.nextLine();
    }

    public static void userList(RESTClientUser clientUser) {
        List<UserDTO> list = clientUser.findAllUsers();

        if (list != null) {
            for (UserDTO userDTO : list) {
                System.out.println("User{id= " + userDTO.getId()
                        + " ,name= " + userDTO.getFirstName() + " " + userDTO.getLastName()
                        + " ,birthday= " + userDTO.getBirthDay().toString()
                        + " ,weight= " + userDTO.getWeight()
                        + " ,gender= " + userDTO.getGender());
            }
        }
    }

    public static void activityCreate(Scanner cons, RESTClientActivity clientActivity) {
        SportActivityDTO activityDTO = new SportActivityDTO();
        CaloriesTableDTO caloriesDTO = new CaloriesTableDTO();
        System.out.print("Enter activity name: ");
        activityDTO.setName(cons.nextLine());

        try {
            System.out.print("Enter activity calories for 60 Kg: ");
            caloriesDTO.setCalories60Kg(cons.nextInt());
            System.out.print("Enter activity calories for 70 Kg: ");
            caloriesDTO.setCalories70Kg(cons.nextInt());
            System.out.print("Enter activity calories for 80 Kg: ");
            caloriesDTO.setCalories80Kg(cons.nextInt());
            System.out.print("Enter activity calories for 90 Kg: ");
            caloriesDTO.setCalories90Kg(cons.nextInt());
        } catch (InputMismatchException ex) {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        caloriesDTO.setGender(Gender.MALE);
        activityDTO.setCalories(caloriesDTO);
        activityDTO = clientActivity.createActivity(activityDTO);

        if (activityDTO != null) {
            System.out.println("Created activity{id= " + activityDTO.getId()
                    + " ,name= " + activityDTO.getName()
                    + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                    + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                    + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                    + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());
        }

        cons.nextLine();
    }

    public static void activityGet(Scanner cons, RESTClientActivity clientActivity) {
        Long id;

        System.out.print("Enter activity id: ");
        try {
            id = cons.nextLong();
        } catch (InputMismatchException ex) {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        SportActivityDTO activityDTO;
        activityDTO = clientActivity.getActivityByID(id);

        if (activityDTO == null) {
            System.out.println("activity not exists");
        } else {
            System.out.println("Activity{id= " + activityDTO.getId()
                    + " ,name= " + activityDTO.getName()
                    + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                    + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                    + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                    + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());
        }

        cons.nextLine();
    }

    public static void activityDelete(Scanner cons, RESTClientActivity clientActivity) {       
        Long id;
        System.out.print("Enter activity id: ");

        try {
            id = cons.nextLong();
        } catch (InputMismatchException ex) {
            System.out.println("input error...try it again");
            cons.nextLine();
            return;
        }

        SportActivityDTO activity = clientActivity.getActivityByID(id);

        if (activity == null) {
            System.out.println("activity not exists");
        } else {
            clientActivity.deleteActivityByActivity(activity);
        }
        cons.nextLine();
    }

    public static void activityList(RESTClientActivity clientActivity) {
        List<SportActivityDTO> list = clientActivity.findAllActivity();

        if (list != null) {
            for (SportActivityDTO activityDTO : list) {
                System.out.println("Activity{id= " + activityDTO.getId()
                        + " ,name= " + activityDTO.getName()
                        + " ,60Kg= " + activityDTO.getCalories().getCalories60Kg()
                        + " ,70Kg= " + activityDTO.getCalories().getCalories70Kg()
                        + " ,80Kg= " + activityDTO.getCalories().getCalories80Kg()
                        + " ,90Kg= " + activityDTO.getCalories().getCalories90Kg());
            }
        }
    }

    public static void entryInfo() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("--------- Welcome to REST client for Sport Manager application ---------");
        System.out.println("------------------------------------------------------------------------");
    }

    public static void info() {
        System.out.println("------------------------------    INFO    ------------------------------");
        System.out.println("entities : activity | user ");
        System.out.println("commands : create | get | delete | list");
        System.out.println("client commands: info | exit \n");

        System.out.println("Example: activity create | user list");
        System.out.println("------------------------------------------------------------------------\n");
    }
}
