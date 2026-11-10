package com.priyanshi.groceryapp;

public class GroceryItem {
    private String name;
    private double pricePerKg;
    private int imageResId;
    private double quantity;

    public GroceryItem(String name, double pricePerKg, int imageResId) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.imageResId = imageResId;
        this.quantity = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return quantity * pricePerKg;
    }
}
