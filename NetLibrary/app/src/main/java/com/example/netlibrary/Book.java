package com.example.netlibrary;

public class Book {
    private String name;
    private String categoryname;
    private float price;
    public Book(String name,String categoryname,float price){
        this.name=name;
        this.categoryname=categoryname;
        this.price=price;
    }
    public String getName(){
        return name;
    }
    public String getCategoryname(){
        return categoryname;
    }
    public float getPrice(){
        return price;
    }

}
