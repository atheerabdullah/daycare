package com.example.daycaresystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

@Table(name = "parent")
public class Parent {

    @Id
    private Integer id;

    @Column
    private String parentName;

    @Column
    private String related;

    @Column
    private String email;

    @Column
    private String password;


    @Column
    private String phone;

    @Column
    private String address;


    // relation between parent and MyUser
    @OneToOne
    @JsonIgnore
    @MapsId
    private MyUser myUser;


    // Parent can have Many bookings
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Booking> bookingSet;


    //Relation O-M one parent with many Children
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parent")
    private Set<Children> childrenSet;



}
