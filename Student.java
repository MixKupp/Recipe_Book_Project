
public class Student {
    private String ID;
    private String pass;
    private String role;

    public Student(){
        this.ID = null;
        this.pass = null;
        this.role = null;
    }

    public Student(String ID, String pass){
        this.ID = ID;
        this.pass = pass;
    }

    public Student(String ID, String pass,String role){
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

    public void setRole(String role){
        this.role = role;
    }

    public String getID(){
        return this.ID;
    }

    public String getPass(){
        return this.pass;
    }

    public String getRole(){
        return this.role;
    }
}
