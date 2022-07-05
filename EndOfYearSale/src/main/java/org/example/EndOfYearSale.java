package org.example;

import java.util.List;

public class EndOfYearSale {


    public static String findLeastExpensive(){
        
        double lowestPrice = 100000;
        String leastExpensiveItem = "";
        
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
    
            for (StoreItem storeItem : inventory) {
                if (storeItem.getCurrentPrice() < lowestPrice){
                    lowestPrice = storeItem.getCurrentPrice();
                    leastExpensiveItem = storeItem.getItemName();
                }
            }
            
            return leastExpensiveItem;
        
    }

    public static void main(String[] args){

    }
}
