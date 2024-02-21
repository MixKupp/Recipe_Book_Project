import java.util.*;

import javax.annotation.processing.FilerException;

import java.io.*;

//Main class
public class main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void invalid() {
        System.out.println("Invalid input please try again.\n");
    }

    public static void main(String[] args) {
        // System
        boolean obj = true;
        clearScreen();
        Scanner input = new Scanner(System.in);
        

        //////////////////////// Login account start////////////////////////
        Student user = new Student();
        Scanner ip_user = new Scanner(System.in);
        Scanner ip_pass = new Scanner(System.in);
        Scanner space = new Scanner(System.in);

        while (obj == true) {
            System.out.print("Welcome To Recipe Book Program.\n1.Register.\n2.Login.(release soon)\n3.Exit.\n\n> ");
            int input_switch1 = input.nextInt();
            switch (input_switch1) {
                // case 1
                case 1:
                    clearScreen();
                    while (obj == true) {
                        System.out.print(">> Register <<\nID :");
                        String input_user = ip_user.nextLine();
                        user.setID(input_user);

                        System.out.print("Pass :");
                        String input_pass = ip_pass.nextLine();
                        user.setPass(input_pass);

                        System.out.println("> Role Header(y/n): ");
                        char input_role = input.next().charAt(0);
                        if (input_role == 'y' || input_role == 'Y') {
                            user.setRole(true);
                        } else if (input_role == 'n' || input_role == 'N') {
                            user.setRole(false);
                        }
                        if (input_user.isEmpty() && input_pass.isEmpty()) {
                            System.out.print("\nPlease enter user & pass input try again.\n\nEnter x for try again.\n");
                            String sp = space.nextLine();
                            clearScreen();
                        } else if (input_user.isEmpty()) {
                            System.out.print("\nPlease enter user input try again.\n\nEnter x for try again.\n");
                            String sp = space.nextLine();
                            clearScreen();
                        } else if (input_pass.isEmpty()) {
                            System.out.print("\nPlease enter pass input try again.\n\nEnter x for try again.\n");
                            String sp = space.nextLine();
                            clearScreen();
                        } else if (input_user != null && input_pass != null) {
                            clearScreen();
                            System.out.println("Register success\n");
                            obj = false;
                        }
                    }
                    // case 2
                case 2:
                    clearScreen();
                    break;
                // case 3
                case 3:
                    clearScreen();
                    System.exit(0);
                default:
                    clearScreen();
                    invalid();
            }
        }

        //////////////////////// Login account end////////////////////////
        obj = true;
        // Build csv file

        //////////////////////// Program start////////////////////////
        if (user.role() == true) {
            while (obj == true) {
                System.out.print("Hello " + user.getID()
                        + "\n\nChoose the option\n\n1.Add recipe\n2.View recipe\n3.Scarch recipe\n4.Remove recipe\n5.Edit recipe\n6.Exit Program\n\n>>> ");
                int input_switch2 = input.nextInt();
                switch (input_switch2) {
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        clearScreen();
                        System.exit(0);
                    default:
                        break;
                }
            }
        } else if (user.role() == false) {
            while (obj == true) {
                System.out.print("Hello " + user.getID()
                        + "\n\nChoose the option\n\n1.View recipe\n2.Scarch recipe\n3.Exit Program\n\n>>> ");
                int input_switch2 = input.nextInt();
                switch (input_switch2) {
                    case 1:
                        clearScreen();
                        
                        System.out.println("Press x to contiue");
                        Scanner spp = new Scanner(System.in);
                        break;

                    case 2:
                        break;
                    case 3:
                        clearScreen();
                        System.exit(0);
                    default:
                        break;
                }
            }
        }

    }
}