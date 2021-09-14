package com.campus.dev.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultDTO<T> {
    private int code = 200;
    private String message = "OK";
    private T data;

    public ResultDTO(T data){
        this.data = data;
    }

    public ResultDTO(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ResultDTO(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
