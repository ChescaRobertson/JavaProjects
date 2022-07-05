package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestChangeTest {

    @Test
    public void calculateAverageChangeListTest(){
        InvestChange investChange = new InvestChange();
        List<Double> purchases = List.of(12.38, 38.29, 5.27, 3.21);
        double estimatedAverageChangeInvested =
                ((13 - 12.38)
                        + (39 - 38.29)
                        + (6 - 5.27)
                        + (4 - 3.21)) / 4;
        assertEquals(estimatedAverageChangeInvested, investChange.calculateAverageChange(purchases));
    }

    @Test
    public void calculateAverageChangeEmptyTest(){
        InvestChange investChange = new InvestChange();
        List<Double> purchases = List.of();
        double estimatedAverageChangeInvested = 0;
        assertEquals(estimatedAverageChangeInvested, investChange.calculateAverageChange(purchases));
    }

    @Test
    public void calculateAverageChangeOneItemTest(){
        InvestChange investChange = new InvestChange();
        List<Double> purchases = List.of(12.38);
        double estimatedAverageChangeInvested = (13-12.38) / 1;
        assertEquals(estimatedAverageChangeInvested, investChange.calculateAverageChange(purchases));
    }
}

