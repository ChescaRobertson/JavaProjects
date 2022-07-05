package org.example;


import java.util.Scanner;

public class WaterBill
{

    public static double calculateWaterBill(double gallonsUsed) {
        double extraCCFs;
        double totalCost = 18.84;
        double numberOfGallonsInCCF = 748;
        double includedGallons = 2 * numberOfGallonsInCCF;

        if (gallonsUsed <= includedGallons) {
            return totalCost;
        } else {
            double additionalGallons = gallonsUsed - includedGallons;
            extraCCFs = Math.ceil(additionalGallons / numberOfGallonsInCCF);
            return totalCost += extraCCFs * 3.90;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many gallons of water " +
                    "did you use this month?");
        double usage = scanner.nextDouble();
        System.out.println("Your water bill is " +
                    calculateWaterBill(usage));
    }
}
