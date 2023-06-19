package com.example.daycaresystem.ApiException;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}

