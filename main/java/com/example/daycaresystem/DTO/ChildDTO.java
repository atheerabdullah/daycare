package com.example.daycaresystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChildDTO {

    private Integer parent_Id;
    private String childName;
    private Integer age;
    private String gender;
    //private String specialNeeds;
    private String disable;
}
