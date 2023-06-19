package com.example.daycaresystem.DTO;


import com.example.daycaresystem.Model.Daycare;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class DaycareDTO {
    private Integer id;
    private String commercialId;
    private String daycareName;
    private String password;
    private String phone;
    private String description;
    private String facilities;
    private String email;


    private Integer capacity;
    private String location;
    private String openingTime;
    private String closingTime;
    private Double rate;

    public DaycareDTO(Daycare daycare) {
        this.id = daycare.getId();
        this.commercialId = daycare.getCommercialId();
        this.daycareName = daycare.getDaycareName();
        this.phone = daycare.getPhone();
        this.description = daycare.getDescription();
        this.facilities = daycare.getFacilities();
        this.email = daycare.getEmail();
        this.capacity = daycare.getCapacity();
        this.location = daycare.getLocation();
        this.openingTime = daycare.getOpeningTime();
        this.closingTime = daycare.getClosingTime();
        this.rate = daycare.getRate();
    }

    public DaycareDTO() {

    }
}
