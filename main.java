import java.util.*;

public class main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void invalid() {
        System.out.println("Invalid Input\n");
    }

    public static void main(String[] args) {
        //System
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
        obj = true;
        Scanner ip_pass = new Scanner(System.in);
        while (obj == true) {
            System.out.print("Pass :");
            String input_pass = ip_pass.nextLine();
            //Check input
            if (input_pass.isEmpty()) {
                clearScreen();
                System.out.println("Welcome To Recipe Book Program\n");
                invalid();
                System.out.print("Register\nID :" + user.getID()+"\n");
            } else if (input_pass != null) {
                user.setPass(input_pass);
                obj = false;
            }
        }
        //////////////////////////////////////////////////////////////////
        System.out.println("Role Header(y/n): ");
        char input_role = input.next().charAt(0);
        if (input_role == 'y' || input_role == 'Y') {
            user.setRole(true);
        } else if (input_role == 'n' || input_role == 'N') {
            user.setRole(false);
        } else {
            invalid();
        }
        //////////////////////// Login account end////////////////////////
        clearScreen();
        //////////////////////// Program start////////////////////////

    }
}