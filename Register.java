import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<Student> accounts;
    File file_acc = new File("data" + File.separator + "Account_ID.csv");

    public Register() {
        this.accounts = new ArrayList<>();
        readAccountsFromCSV();
    }

    public void readAccountsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(file_acc))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    accounts.add(new Student(username, password, role));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerAccount(String username, String password, String role) {
        List<String> existingUsernames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data" + File.separator + "Account_ID.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    existingUsernames.add(parts[0]); // Assuming username is in the first column
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        if (existingUsernames.contains(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return; // Exit the method
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data" + File.separator + "Account_ID.csv", true))) {
            bw.write(username + "," + password + "," + role);
            bw.newLine();
            System.out.println("Account registered successfully for username: " + username);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeAccountToCSV(String username, String password, String role) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_acc, true))) {
            bw.write(username + "," + password + "," + role);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        return accounts.stream().anyMatch(account -> account.getID().equals(username)
                && account.getPass().equals(password));
    }

    public String checkRole(String username, String password) {
        for (Student account : accounts) {
            if (account.getID().equals(username) && account.getPass().equals(password)) {
                return account.getRole();
            }
        }
        return null;
    }

    public boolean isUsernameExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("data" + File.separator + "Account_ID.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
