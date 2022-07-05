package org.example;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordComplexity
{
    public static boolean isComplex(String password){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}");
        Matcher matcher = pattern.matcher(password);
        return (matcher.find());
    }

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a password: ");
        String password = scanner.nextLine();
        System.out.println("Is the password complex? " + isComplex(password));
    }
}
