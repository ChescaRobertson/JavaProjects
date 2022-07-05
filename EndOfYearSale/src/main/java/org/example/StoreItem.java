package org.example;

import java.text.DecimalFormat;

public class StoreItem {

    private String itemName;
    private double retailPrice;
    private double discount;

    private double currentPrice;

    public StoreItem(String itemName, double retailPrice, double discount){
        this.itemName = itemName;
        this.retailPrice = retailPrice;
        this.discount = discount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCurrentPrice() {
        setCurrentPrice();
        return currentPrice;
    }

    public void setCurrentPrice() {
        this.currentPrice = Math.round(this.retailPrice - (this.retailPrice * this.discount));
    }

}
