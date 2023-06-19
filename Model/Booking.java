package com.example.daycaresystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties({"totalPrice", "packageObj", "rating", "apackage"})
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String serviceType;

    @Column
    private String durationType;

    @Column
    private String status = "in progress";

    @Column
    private String address;

    @Column
    private String childName;

    @Column
    private double totalPrice;

    @Column
    private Integer Start_time;

    @Column
    private Integer End_time;





    // many Bookings for one Parent
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_id", referencedColumnName = "my_user_id")
    private Parent parent;


    // service with package > many packages have on booking
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package packageObj;


    // booking with rate > one book have to do one rate
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "booking1")
    @PrimaryKeyJoinColumn
   private Rating rating ;

    // book with package - many bookings with only one package
    @ManyToOne
    private Package aPackage;



}
