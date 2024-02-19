import java.util.*;
import java.io.*;

//Main class
public class main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void invalid() {
        System.out.println("Invalid Input\n");
    }

    public static void main(String[] args){
        // System
        boolean obj = true;
        clearScreen();
        Scanner input = new Scanner(System.in);
  
        //////////////////////// Login account start////////////////////////
        System.out.println("Welcome To Recipe Book Program");
        Student user = new Student();
        Scanner ip_user = new Scanner(System.in);
        while (obj == true) {
            System.out.print("Register\nID :");
            String input_user = ip_user.nextLine();
            // Check input
            if (input_user.isEmpty()) {
                clearScreen();
                System.out.println("Welcome To Recipe Book Program\n");
                invalid();
            } else if (input_user != null) {
                user.setID(input_user);
                obj = false;
            }
        }
        /////////////////////////////////////////////////////////////////
        Scanner ip_pass = new Scanner(System.in);
        while (obj == false) {
            System.out.print("Pass :");
            String input_pass = ip_pass.nextLine();
            // Check input
            if (input_pass.isEmpty()) {
                clearScreen();
                System.out.println("Welcome To Recipe Book Program\n");
                invalid();
                System.out.print("Register\nID :" + user.getID() + "\n");
            } else if (input_pass != null) {
                user.setPass(input_pass);
                obj = true;
            }
        }

        //////////////////////////////////////////////////////////////////
        while (obj == true) {
            System.out.println("Role Header(y/n): ");
            char input_role = input.next().charAt(0);
            if (input_role == 'y' || input_role == 'Y') {
                user.setRole(true);
                obj = false;
            } else if (input_role == 'n' || input_role == 'N') {
                user.setRole(false);
                obj = false;
            } else {
                clearScreen();
                System.out.println("Welcome To Recipe Book Program\n");
                invalid();
                System.out.print("Register\nID :" + user.getID() + "\nPass :"+ user.getPass()+"\n");
            }
        }
        
        //////////////////////// Login account end////////////////////////
        clearScreen();
        //Build csv file 
        
        //////////////////////// Program start////////////////////////

        while(obj == false){
            System.out.print("Welcome To Recipe Book Program\nChoose the option\n1.Add recipe\n2.View recipe\n3.Scarch recipe\n4.Remove recipe\n5.Edit recipe\n6.Exit Program\n\n>>> ");
            int input_switch = input.nextInt();
            switch (input_switch) {
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
    }
}