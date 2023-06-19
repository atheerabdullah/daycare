package com.example.daycaresystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class RatingDTO {


    private Integer id;
    private Double rate;
    private String comments;

    public RatingDTO() {

    }
}
