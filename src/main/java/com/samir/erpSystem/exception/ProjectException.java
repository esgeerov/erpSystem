package com.samir.erpSystem.exception;

public class ProjectException extends RuntimeException{
    private Integer code;
    public ProjectException(Integer code,String message){
        super(message);
        this.code=code;
    }
    public Integer getCode(){
        return code;
    }
}

