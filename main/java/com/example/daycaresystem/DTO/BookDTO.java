package com.example.daycaresystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {

    private Integer id;
    private Integer parentId;
    private Integer PackageId;


    private Integer start_time;
    private Integer end_time;
    private double totalPrice;
    private String serviceType;
    private String durationType;
    private String status;
    private String address;
    private String childName;


    public BookDTO() {

    }
}
