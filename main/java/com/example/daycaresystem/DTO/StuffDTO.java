package com.example.daycaresystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StuffDTO {

    private Integer daycareId;
    private Integer civilRecord;
    private String stuffName;
    private Integer stuffId;
    private String phone;
    private String position;
    private String qualifications;

    public StuffDTO() {

    }
}