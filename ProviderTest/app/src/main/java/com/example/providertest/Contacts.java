package com.example.providertest;

public class Contacts {
    private String name;
    private String number;
    private String sex;
    public Contacts(String name,String number,String sex){
        this.name=name;
        this.number=number;
        this.sex=sex;
    }
    public String getName(){
        return name;
    }
    public String getNumber(){
        return number;
    }
    public String getSex(){
        return sex;
    }
}
