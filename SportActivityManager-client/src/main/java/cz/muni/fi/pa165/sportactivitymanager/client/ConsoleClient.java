package cz.muni.fi.pa165.sportactivitymanager.client;

import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Petr Jelinek
 */
public class ConsoleClient {

    public static void main(String[] args) {
        RESTClientActivity clientActivity = new RESTClientActivity();
        RESTClientUser clientUser = new RESTClientUser();

        entryInfo();
        info();

        Scanner sc = new Scanner(System.in);
        String choice = null;

        while (!"exit".equals(choice)) {
            System.out.print("Enter your command: ");
            choice = sc.nextLine();

            switch (choice) {
                case "info":
                    info();
                    break;
                case "user create":
                    userCreate(sc, clientUser);
                    break;
                case "user get":
                    userGet(sc, clientUser);
                    break;
                case "user delete":
                    userDelete(sc, clientUser);
                    break;
                case "user list":
                    userList(clientUser);
                    break;
                case "activity create":
                    activityCreate(sc, clientActivity);
                    break;
                case "activity get":
                    activityGet(sc, clientActivity);
                    break;
                case "activity delete":
                    activityDelete(sc, clientActivity);
                    break;
                case "activity list":
                    activityList(clientActivity);
                    break;
                default:
                    if (!choice.equals("exit")) {
                        System.out.println("Command NOT recognized... try \"info\"");
                    }
            }
        }
        sc.close();
    }

    public static void userCreate(Scanner sc, RESTClientUser clientUser) {
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(InputValidator.getString(sc, "Enter first name: "));
        userDTO.setLastName(InputValidator.getString(sc, "Enter last name: "));
        userDTO.setBirthDay(InputValidator.getDate(sc, "birthday"));
        userDTO.setWeight(InputValidator.getInt(sc, "Enter weight: "));
        userDTO.setGender(InputValidator.getGender(sc));

        userDTO = clientUser.createUser(userDTO);

        if (userDTO != null) {
            System.out.println("User created " + userDTO);
        }
    }

    public static void userGet(Scanner sc, RESTClientUser clientUser) {
        Long id = InputValidator.getLong(sc, "Enter user id: ");
        UserDTO userDTO = clientUser.getUserByID(id);

        if (userDTO == null) {
            System.out.println("user not exists");
        } else {
            System.out.println("User " + userDTO);
        }
    }

    public static void userList(RESTClientUser clientUser) {
        List<UserDTO> list = clientUser.findAllUsers();

        if (list != null) {
            for (UserDTO userDTO : list) {
                System.out.println("User " + userDTO);
            }
        }
    }

    public static void userDelete(Scanner sc, RESTClientUser clientUser) {
        Long id = InputValidator.getLong(sc, "Enter user id: ");

        UserDTO userDTO = clientUser.getUserByID(id);

        if (userDTO == null) {
            System.out.println("user not exists");
        } else {
            clientUser.deleteUserByUser(userDTO);
        }
    }

    public static void activityCreate(Scanner sc, RESTClientActivity clientActivity) {
        SportActivityDTO activityDTO = new SportActivityDTO();
        CaloriesTableDTO caloriesDTO = new CaloriesTableDTO();

        activityDTO.setName(InputValidator.getString(sc,
                "Enter activity name: "));
        caloriesDTO.setCalories60Kg(InputValidator.getInt(sc,
                "Enter activity calories for 60 Kg: "));
        caloriesDTO.setCalories70Kg(InputValidator.getInt(sc,
                "Enter activity calories for 70 Kg: "));
        caloriesDTO.setCalories80Kg(InputValidator.getInt(sc,
                "Enter activity calories for 80 Kg: "));
        caloriesDTO.setCalories90Kg(InputValidator.getInt(sc,
                "Enter activity calories for 90 Kg: "));
        caloriesDTO.setGender(Gender.MALE);

        activityDTO.setCalories(caloriesDTO);

        activityDTO = clientActivity.createActivity(activityDTO);

        if (activityDTO != null) {
            System.out.println("Activity created " + activityDTO);
        }
    }

    public static void activityGet(Scanner cons, RESTClientActivity clientActivity) {
        Long id = InputValidator.getLong(cons, "Enter activity id: ");
        SportActivityDTO activityDTO = clientActivity.getActivityByID(id);

        if (activityDTO == null) {
            System.out.println("activity not exists");
        } else {
            System.out.println("Activity " + activityDTO);
        }
    }

    public static void activityDelete(Scanner cons, RESTClientActivity clientActivity) {
        Long id = InputValidator.getLong(cons, "Enter activity id: ");
        SportActivityDTO activityDTO = clientActivity.getActivityByID(id);

        if (activityDTO == null) {
            System.out.println("activity not exists");
        } else {
            clientActivity.deleteActivityByActivity(activityDTO);
        }
    }

    public static void activityList(RESTClientActivity clientActivity) {
        List<SportActivityDTO> list = clientActivity.findAllActivity();

        if (list != null) {
            for (SportActivityDTO activityDTO : list) {
                System.out.println("Activity " + activityDTO);
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
        System.out.println("------------------------------------------------------------------------");
    }
}
