public class Admin {

    private String name;
    private String password;

    public Admin(String adminname, String adminpassword) {
        this.name = adminname;
        this.password = adminpassword;
    }

    public String getName()  {return name;}

    public String getPassword() {return password;}

}
