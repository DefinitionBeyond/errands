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
    private long pageNum;

    public ResultListDTO(T data, long total,long pageNum ){
        this.data = data;
        this.total = total;
        this.pageNum = pageNum;
    }

}
