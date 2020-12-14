package com.company;

public class FactoryContact {
    public static Contact getContact(){
        return new Contact();
    }
}
