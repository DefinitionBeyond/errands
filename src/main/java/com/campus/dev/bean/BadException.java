package com.campus.dev.bean;

public class BadException extends RuntimeException{

    private String messge;

    public BadException(){
        super();
    }

    public BadException(String messge){
        this.messge = messge;
    }
}
