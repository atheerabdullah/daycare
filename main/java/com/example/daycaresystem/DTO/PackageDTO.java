package com.example.daycaresystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@Data
public class PackageDTO {


    private Integer daycareId;
    private String description;
    private String packageType;
    private String packageDuration;
    private Double packagePrice;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public PackageDTO() {

    }
}
