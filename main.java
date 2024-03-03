import java.util.*;

import javax.annotation.processing.FilerException;

import obj.Recipe;
import obj.Register;
import obj.Student;

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

    public static void Addrecipe() {
        File file = new File("data" + File.separator + "Recipe_Book.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            while (true) {
                Scanner ct = new Scanner(System.in);
                Scanner nm = new Scanner(System.in);
                Scanner rc = new Scanner(System.in);
    
                System.out.print(
                    ">> Choose Category <<\n1.Appetizer\n2.Main course\n3.Dessert\n4.Drink\n\n>> ");
                String c = ct.nextLine();
                String cc = "";
                switch (c) {
                    case "1":
                        cc = "Appetizer";
                        break;
                    case "2":
                        cc = "Main course";
                        break;
                    case "3":
                        cc = "Dessert";
                        break;
                    case "4":
                        cc = "Drink";
                        break;
                    default:
                        clearScreen();
                        invalid();
                        continue;
                }
    
                System.out.print(">> Food name : ");
                String n = nm.nextLine();
                System.out.print(">> Recipe : ");
                String r = rc.nextLine();
                if (n.isEmpty() || r.isEmpty()) {
                    clearScreen();
                    invalid();
                    continue;
                } else {
                    bw.newLine();
                    bw.write(cc + "," + n + "," + r);
                    clearScreen();
                    System.out.println("Data written to the file successfully.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerAccount(String username, String password, String role) {
        File file_acc = new File("data" + File.separator + "Account_ID.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_acc, true))) {
            bw.write(username + "," + password + "," + role);
            bw.newLine();
            System.out.println("Account registered successfully!");
        } catch (IOException e) {
            System.err.println("Already have account.");
        }
    }

    public static void searchRecipe(String keyword) {
        try (BufferedReader br = new BufferedReader(new FileReader("data" + File.separator + "Recipe_Book.csv"))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && (parts[1].contains(keyword) || parts[1].matches(".*\\b" + keyword + "\\b.*"))) {
                    System.out.println("Category: " + parts[0]);
                    System.out.println("Name: " + parts[1]);
                    System.out.println("Recipe: " + "\n     "+parts[2] + "\n");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Recipe not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editRecipe() {
        List<String> recipes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data" + File.separator + "Recipe_Book.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                recipes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (recipes.isEmpty()) {
            System.out.println("No recipes found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the keyword to search for: ");
        String keyword = scanner.nextLine();

        List<String> matchedRecipes = new ArrayList<>();
        for (String recipe : recipes) {
            String[] parts = recipe.split(",");
            if (parts.length > 1 && parts[1].equalsIgnoreCase(keyword)) {
                matchedRecipes.add(recipe);
            }
        }

        if (matchedRecipes.isEmpty()) {
            System.out.println("No recipes found for the keyword: " + keyword);
            return;
        }

        System.out.println("Select a recipe to edit: ");
        for (int i = 0; i < matchedRecipes.size(); i++) {
            System.out.println((i + 1) + ". " + matchedRecipes.get(i));
        }
        System.out.print("Enter the number of the recipe to edit: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice < 1 || choice > matchedRecipes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Enter the new recipe: ");
        String newRecipe = scanner.nextLine();

        String editedRecipe = matchedRecipes.get(choice - 1).split(",")[0] + ","
                + matchedRecipes.get(choice - 1).split(",")[1] + "," + newRecipe;
        recipes.set(recipes.indexOf(matchedRecipes.get(choice - 1)), editedRecipe);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data" + File.separator + "Recipe_Book.csv"))) {
            for (String updatedRecipe : recipes) {
                bw.write(updatedRecipe);
                bw.newLine();
            }
            clearScreen();
            System.out.println("Recipe edited successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewAllRecipes() {
        try (BufferedReader br = new BufferedReader(new FileReader("data" + File.separator + "Recipe_Book.csv"))) {
            String line;
            System.out.println("------------------------------------");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 2) {
                    System.out.println("Category: " + parts[0]);
                    System.out.println("Name    : " + parts[1]);
                    System.out.println("Recipe  : " + parts[2]);
                    System.out.println("------------------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeRecipe() {
        List<String> recipes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data" + File.separator + "Recipe_Book.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                recipes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (recipes.isEmpty()) {
            System.out.println("No recipes found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the keyword to search for: ");
        String keyword = scanner.nextLine();

        List<String> matchedRecipes = new ArrayList<>();
        for (String recipe : recipes) {
            String[] parts = recipe.split(",");
            if (parts.length > 1 && parts[1].equalsIgnoreCase(keyword)) {
                matchedRecipes.add(recipe);
            }
        }

        if (matchedRecipes.isEmpty()) {
            System.out.println("No recipes found for the keyword: " + keyword);
            return;
        }

        System.out.println("Select a recipe to remove: ");
        for (int i = 0; i < matchedRecipes.size(); i++) {
            System.out.println((i + 1) + ". " + matchedRecipes.get(i));
        }
        System.out.print("Enter the number of the recipe to remove: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice < 1 || choice > matchedRecipes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        recipes.remove(matchedRecipes.get(choice - 1));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data" + File.separator + "Recipe_Book.csv"))) {
            for (String updatedRecipe : recipes) {
                bw.write(updatedRecipe);
                bw.newLine();
            }
            clearScreen();
            System.out.println("Recipe removed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Register register = new Register();
        ArrayList<Recipe> recipeList = new ArrayList<>();

        //////////////////////// Login account start////////////////////////
        Student user = new Student();
        Scanner ip_user = new Scanner(System.in);
        Scanner ip_pass = new Scanner(System.in);
        Scanner space = new Scanner(System.in);

        while (obj == true) {
            System.out.print("Welcome To Recipe Book Program.\n1.Register.\n2.Login.\n3.Exit.\n\n> ");
            char input_switch1 = input.next().charAt(0);
            if (input_switch1 == '1' || input_switch1 == '2' || input_switch1 == '3')
                switch (input_switch1) {
                    // case 1
                    case '1':
                        clearScreen();
                        while (obj == true) {
                            System.out.print(">> Register <<\nID : ");
                            String input_user = ip_user.nextLine();
                            user.setID(input_user);

                            System.out.print("Pass : ");
                            String input_pass = ip_pass.nextLine();
                            user.setPass(input_pass);
                            clearScreen();

                            System.out.print(">> Role <<\n1.Creator\n2.Viewer\n>  ");
                            char input_role = input.next().charAt(0);
                            if (input_role == '1') {
                                user.setRole("Creator");
                            } else if (input_role == '2') {
                                user.setRole("Viewer");
                            }
                            if (input_user.isEmpty() && input_pass.isEmpty()) {
                                System.out.print(
                                        "\nPlease enter user & pass input try again.\n\nEnter for try again.\n");
                                String sp = space.nextLine();
                                clearScreen();
                                continue;
                            } else if (input_user.isEmpty()) {
                                System.out.print(
                                        "\nPlease enter user input try again.\n\nEnter for try again.\n");
                                String sp = space.nextLine();
                                clearScreen();
                                continue;
                            } else if (input_pass.isEmpty()) {
                                System.out.print(
                                        "\nPlease enter pass input try again.\n\nEnter for try again.\n");
                                String sp = space.nextLine();
                                clearScreen();
                                continue;
                            }
                            if (register.isUsernameExists(input_user)) {
                                clearScreen();
                                System.out.println("Username already exists. Please choose a different username.\n");
                                continue;
                            }

                            clearScreen();
                            register.registerAccount(input_user, input_pass, user.getRole());
                            obj = false;
                        }
                        // case 2
                    case '2':
                        clearScreen();
                        while (obj == true) {
                            System.out.print(">> Login <<\nID : ");
                            String input_user = ip_user.nextLine();
                            user.setID(input_user);

                            System.out.print("Pass : ");
                            String input_pass = ip_pass.nextLine();
                            user.setPass(input_pass);

                            if (register.login(input_user, input_pass)) {
                                clearScreen();
                                System.out.println("Login successful!\n");
                                String role = register.checkRole(input_user, input_pass);
                                if ("Creator".equals(role)) {
                                    System.out.print("Hello " + user.getID());
                                    while (obj == true) {
                                        System.out.print(
                                                "\n>> Choose the option <<\n\n1.Add recipe\n2.View recipe\n3.Search recipe\n4.Remove recipe\n5.Edit recipe\n6.Exit Program\n\n>>> ");
                                        char input_switch2 = input.next().charAt(0);
                                        if (input_switch2 == '1' || input_switch2 == '2' || input_switch2 == '3'
                                                || input_switch2 == '4' || input_switch2 == '5'
                                                || input_switch2 == '6') {
                                            switch (input_switch2) {
                                                case '1':
                                                    clearScreen();
                                                    scanner.nextLine();
                                                    Addrecipe();
                                                    break;
                                                case '2':
                                                    clearScreen();
                                                    viewAllRecipes();
                                                    System.out.println();
                                                    System.out.println(
                                                            "##################################################################");
                                                    break;
                                                case '3':
                                                    clearScreen();
                                                    Scanner ip = new Scanner(System.in);
                                                    System.out.print("Entry food name: ");
                                                    String ipName = ip.nextLine();
                                                    System.out.println();
                                                    searchRecipe(ipName);
                                                    break;
                                                case '4':
                                                    clearScreen();
                                                    removeRecipe();
                                                    break;
                                                case '5':
                                                    clearScreen();
                                                    editRecipe();
                                                    break;
                                                case '6':
                                                    clearScreen();
                                                    System.exit(0);
                                                default:
                                                    break;
                                            }
                                        } else {
                                            clearScreen();
                                            invalid();
                                            continue;
                                        }
                                    }
                                } else if ("Viewer".equals(role)) {
                                    System.out.print("Hello " + user.getID());
                                    while (obj == true) {
                                        System.out.print(
                                                "\n>> Choose the option <<\n\n1.View recipe\n2.Scarch recipe\n3.Exit Program\n\n>>> ");
                                        char input_switch2 = input.next().charAt(0);
                                        if (input_switch2 == '1' || input_switch2 == '2' || input_switch2 == '3') {
                                            switch (input_switch2) {
                                                case '1':
                                                    clearScreen();
                                                    viewAllRecipes();
                                                    System.out.println();
                                                    System.out.println(
                                                            "##################################################################");
                                                    break;

                                                case '2':
                                                    clearScreen();
                                                    Scanner ip = new Scanner(System.in);
                                                    System.out.print("Entry food name: ");
                                                    String ipName = ip.nextLine();
                                                    System.out.println();
                                                    searchRecipe(ipName);
                                                    break;
                                                case '3':
                                                    clearScreen();
                                                    System.exit(0);
                                                default:
                                                    break;
                                            }
                                        } else {
                                            clearScreen();
                                            invalid();
                                            continue;
                                        }
                                    }
                                }
                                obj = false;
                                break;
                            } else {
                                clearScreen();
                                System.out.println("Invalid username or password try again.");
                                continue;
                            }
                        }
                        break;
                    // case 3
                    case '3':
                        clearScreen();
                        System.exit(0);
                    default:
                        clearScreen();
                        invalid();
                }
            else {
                clearScreen();
                invalid();
                continue;
            }
        }

        //////////////////////// Login account end////////////////////////
        obj = true;
        // Build csv file
        //////////////////////// Program start////////////////////////
        if (user.getRole() == "Creator") {
            System.out.print("Hello " + user.getID());
            while (obj == true) {
                System.out.print(
                        "\n>> Choose the option <<\n\n1.Add recipe\n2.View recipe\n3.Search recipe\n4.Remove recipe\n5.Edit recipe\n6.Exit Program\n\n>>> ");
                char input_switch2 = input.next().charAt(0);
                if (input_switch2 == '1' || input_switch2 == '2' || input_switch2 == '3' || input_switch2 == '4'
                        || input_switch2 == '5' || input_switch2 == '6') {
                    switch (input_switch2) {
                        case 1:
                            clearScreen();
                            scanner.nextLine();
                            Addrecipe();
                            break;
                        case 2:
                            clearScreen();
                            viewAllRecipes();
                            System.out.println();
                            System.out.println("##################################################################");
                            break;
                        case 3:
                            clearScreen();
                            Scanner ip = new Scanner(System.in);
                            System.out.print("Entry food name: ");
                            String ipName = ip.nextLine();
                            System.out.println();
                            searchRecipe(ipName);
                            break;
                        case 4:
                            clearScreen();
                            removeRecipe();
                            break;
                        case 5:
                            clearScreen();
                            editRecipe();
                            break;
                        case 6:
                            clearScreen();
                            System.exit(0);
                        default:
                            break;
                    }
                } else {
                    clearScreen();
                    invalid();
                    continue;
                }
            }
        } else if (user.getRole() == "Viewer") {
            System.out.print("Hello " + user.getID());
            while (obj == true) {
                System.out.print("\n>> Choose the option <<\n\n1.View recipe\n2.Search recipe\n3.Exit Program\n\n>>> ");
                char input_switch2 = input.next().charAt(0);
                if (input_switch2 == '1' || input_switch2 == '2' || input_switch2 == '3') {
                    switch (input_switch2) {
                        case 1:
                            clearScreen();
                            viewAllRecipes();
                            System.out.println();
                            System.out.println("##################################################################");
                            break;

                        case 2:
                            clearScreen();
                            Scanner ip = new Scanner(System.in);
                            System.out.print("Entry food name: ");
                            String ipName = ip.nextLine();
                            System.out.println();
                            searchRecipe(ipName);
                            break;
                        case 3:
                            clearScreen();
                            System.exit(0);
                        default:
                            break;
                    }
                } else {
                    clearScreen();
                    invalid();
                    continue;
                }
            }
        }
    }
}