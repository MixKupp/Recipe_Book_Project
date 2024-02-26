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

    public static void main(String[] args) throws FileNotFoundException {
        // System
        boolean obj = true;
        clearScreen();
        Scanner input = new Scanner(System.in);
        File file_acc = new File("data" + File.separator + "Account_ID.csv");
        File file = new File("data" + File.separator + "Recipe_Book.csv");
        Scanner scanner_acc = new Scanner(file_acc);
        Scanner scanner = new Scanner(file);
        ArrayList<Recipe> recipeList = new ArrayList<>();

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
                        clearScreen();

                        System.out.print(">> Role <<\n1.Creator\n2.Viewer\n>  ");
                        int input_role = input.nextInt();
                        if (input_role == 1) {
                            user.setRole(true);
                        } else if (input_role == 2) {
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
            System.out.print("Hello " + user.getID());
            while (obj == true) {
                System.out.print("\nChoose the option\n\n1.Add recipe\n2.View recipe\n3.Scarch recipe\n4.Remove recipe\n5.Edit recipe\n6.Exit Program\n\n>>> ");
                int input_switch2 = input.nextInt();
                switch (input_switch2) {
                    case 1:
                        
                        break;
                    case 2:
                        clearScreen();
                        scanner.nextLine();
                        while (scanner.hasNextLine()) {
                            String dataline = scanner.nextLine();

                            StringTokenizer tokenizer = new StringTokenizer(dataline, ",");

                            String category = tokenizer.nextToken().trim();
                            String name = tokenizer.nextToken().trim();
                            String recipe = tokenizer.nextToken().trim();

                            recipeList.add(new Recipe(category, name, recipe));
                        }
                        scanner.close();

                        for (Recipe recipe : recipeList) {
                            System.out.println(
                                    recipe.getCategory() + "| " + recipe.getfoodName() + " -> " + recipe.getRecipe());
                        }
                        System.out.println();
                        System.out.println("##################################################################");
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
            System.out.print("Hello " + user.getID());
            while (obj == true) {
                System.out.print("\nChoose the option\n\n1.View recipe\n2.Scarch recipe\n3.Exit Program\n\n>>> ");
                int input_switch2 = input.nextInt();
                switch (input_switch2) {
                    case 1:
                        clearScreen();
                        scanner.nextLine();
                        while (scanner.hasNextLine()) {
                            String dataline = scanner.nextLine();

                            StringTokenizer tokenizer = new StringTokenizer(dataline, ",");

                            String category = tokenizer.nextToken().trim();
                            String name = tokenizer.nextToken().trim();
                            String recipe = tokenizer.nextToken().trim();

                            recipeList.add(new Recipe(category, name, recipe));
                        }
                        scanner.close();

                        for (Recipe recipe : recipeList) {
                            System.out.println(
                                    recipe.getCategory() + "| " + recipe.getfoodName() + " -> " + recipe.getRecipe());
                        }
                        System.out.println();
                        System.out.println("##################################################################");
                        break;

                    case 2:
                        clearScreen();
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