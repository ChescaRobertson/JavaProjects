package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndOfYearSaleTest {

    @Test
    public void getItemNameTest(){
       StoreItem item = new StoreItem("Hat",
                23.99, .1);
        assertEquals("Hat", item.getItemName());
    }

    @Test
    public void getItemRetailPriceTest(){
        StoreItem item = new StoreItem("Hat",
                23.99, .1);
        assertEquals(23.99, item.getRetailPrice());
    }

    @Test
    public void getItemDiscountTest(){
        StoreItem item = new StoreItem("Hat",
                23.99, .1);
        assertEquals(0.1, item.getDiscount());
    }

    @Test
    public void getItemCurrentPriceTest(){
        StoreItem item = new StoreItem("Hat",
                23.99, .1);
        assertEquals(22.0, item.getCurrentPrice());
    }

    @Test
    public void LeastExpensiveItemTest(){
        List<StoreItem> inventory = List.of(
                new StoreItem("T-Shirt",
                        19.99, .4),
                new StoreItem("Dress",
                        34.99, .75),
                new StoreItem("Record Player",
                        92.99, .75),
                new StoreItem("Hat",
                        23.99, .1),
                new StoreItem("Jeans",
                        54.99, .65));

        assertEquals(("Dress"), EndOfYearSale.findLeastExpensive());

    }
}
