package com.example.helper.Entity;

public class Product {
    private String Name;
    private String shop;
    private int HowFar;
    private int price;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHowFar() {
        return HowFar;
    }

    public void setHowFar(int howFar) {
        HowFar = howFar;
    }
}
