package org.example;

import java.time.LocalDate;

public class HundredDays {

    public static LocalDate calculateDays(LocalDate today){
        return today.plusDays(100);
    }

    public static void main(String[] args){
        LocalDate today = LocalDate.now();
        System.out.println("100 days from now is:  " + calculateDays(today));

    }
}
