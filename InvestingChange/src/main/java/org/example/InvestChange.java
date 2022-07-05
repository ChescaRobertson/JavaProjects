package org.example;

import java.util.ArrayList;
import java.util.List;

public class InvestChange
{

//    public static double calculateAverageChange(List<Double> purchases){
//
//       double totalChange = 0;
//       for(double number : purchases) {
//           double change = 1 - (number % 1);
//           totalChange += change;
//       }
//
//       if (totalChange == 0) {
//           return 0;
//        } else {
//           return totalChange/ purchases.size();
//       }
//
//    }

    public static double calculateAverageChange(List<Double> purchases){
        return purchases.stream()
                .mapToDouble(x -> Math.ceil(x) - x)
                .average().orElse(0);
    }

    public static void main( String[] args )
    {
        List<Double> purchases = List.of(12.38, 38.29, 5.27, 3.21);
        System.out.println(calculateAverageChange(purchases));
    }
}
