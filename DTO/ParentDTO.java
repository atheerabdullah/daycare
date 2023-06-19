package com.example.daycaresystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ParentDTO {

    private String parentName;
    private String address;
    private String email;
     private String password;
    private String phone;
    private String related;


    private List<BookDTO> bookings;
}
