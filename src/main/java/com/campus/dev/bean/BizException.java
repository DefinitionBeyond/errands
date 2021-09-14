package com.campus.dev.bean;


import lombok.Getter;

@Getter
public class BizException extends Exception{

    private int code;
    private String messge;

    public BizException(){

    }

    public BizException(int code){
        this.code = code;
    }

    public BizException(int code, String messge){
        this.code = code;
        this.messge = messge;
    }
}
