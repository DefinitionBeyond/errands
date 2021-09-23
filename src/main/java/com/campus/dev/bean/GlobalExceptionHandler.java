package com.campus.dev.bean;

import com.campus.dev.dto.ResultDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO handlerException(Exception e){
        ResultDTO resultDTO = ResultDTO.builder().code(500).message(e.getMessage()).build();
        return resultDTO;
    }

    @ExceptionHandler(BadException.class)
    @ResponseBody
    public ResultDTO handlerBadException(BadException e){
        ResultDTO resultDTO = ResultDTO.builder().code(400).message(e.getMessge()).build();

        return  resultDTO;
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResultDTO handlerBadException(BizException e){
        ResultDTO resultDTO = ResultDTO.builder().code(e.getCode()).message(e.getMessage()).build();

        return  resultDTO;
    }

}
