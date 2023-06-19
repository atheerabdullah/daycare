package com.example.daycaresystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ShowDTO {

    private ShowDTO showDTO;
    private Integer id;

    private String daycareName;

    private String phone;
    private String description;
    private String facilities;
    private String email;

    private Integer capacity;
    private String location;
    private String openingTime;
    private String closingTime;
    private Double rate;
}
