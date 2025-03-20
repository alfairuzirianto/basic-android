package com.example.latianfragment;

public class Item {
    private String name;
    private String price;
    private int imageResId;

    public Item(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}

