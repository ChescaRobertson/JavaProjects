package emailapp;

import java.util.Locale;
import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private int mailboxCapacity = 500;
    private int defaultPasswordLength = 10;
    private String email;
    private String alternateEmail;
    private String companySuffix = "frcompany.com";

    // Constructor to receive first and last name
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Call method to ask for department and return
        this.department = setDepartment();

        // Call a method to return a random password
        this.password = randomPassword(defaultPasswordLength);
        System.out.println("Generated password: " + this.password);

        //Combine elements to generate email address
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + "." + companySuffix;
    }

    // Ask for department
    private String setDepartment() {
        System.out.print("New worker: " + firstName + lastName + "\nDepartment codes: \n1 for Sales\n2 for Development\n3 for Accounting\n0 for none\nEnter department code: ");
        Scanner in = new Scanner(System.in);
        int depChoice = in.nextInt();
        if (depChoice == 1) {
            return "sales";
        } else if (depChoice == 2) {
            return "dev";
        } else if (depChoice == 3) {
            return "acct";
        } else {
            return "";
        }
    }

    // Generate random password
    private String randomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    // Set mailbox capacity
    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    // Set alternative email
    public void setAlternateEmail(String altEmail) {
        this.alternateEmail =altEmail;
    }

    // Change password
    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    //Get Methods
    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public String getPassword() {
        return password;
    }

    public String showInfo(){
        return "Display Name: " + firstName + lastName +
                "\nCompany email: " + email +
                "\nMailbox Capacity: " + mailboxCapacity + "mb";
    }
}
