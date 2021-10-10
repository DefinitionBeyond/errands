package com.campus.dev.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultListDTO<T> {
    private int code = 200;
    private String message = "OK";
    private T data;
    private long total;

    public ResultListDTO(T data, long total ){
        this.data = data;
        this.total = total;
    }

}
