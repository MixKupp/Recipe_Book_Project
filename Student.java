
public class Student {
    private String ID;
    private String pass;
    private boolean role;

    public Student(){
        this.ID = null;
        this.pass = null;
        this.role = false;
    }

    public Student(String ID, String pass,boolean role){
        this.ID = ID;
        this.pass = pass;
        this.role = role;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    public void setRole(boolean role){
        this.role = role;
    }

    public String getID(){
        return this.ID;
    }

    public String getPass(){
        return this.pass;
    }
    public String getRole(){
        String print = ""; 
        if (role == true){
            print = "Creator";
        }
        else if (role == false){
            print = "Viewer";
        }
        else{
            role = false;
        }
        return print;
    }
    public boolean role(){
        return this.role;
    }
}
