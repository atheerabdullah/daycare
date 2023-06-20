package com.example.daycaresystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RateBookDTO {

    private Integer bookId;
    private Integer rating=0;
    private String comment;

}
