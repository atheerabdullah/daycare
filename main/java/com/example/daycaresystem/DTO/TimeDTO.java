package com.example.daycaresystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TimeDTO {

    private String openingTime;
    private String closingTime;


    public TimeDTO() {

    }
}
