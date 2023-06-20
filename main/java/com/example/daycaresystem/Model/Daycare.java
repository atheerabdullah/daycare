package com.example.daycaresystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

@Table(name = "daycare")
public class Daycare {

    @Id
    private Integer id;


    @Column(unique=true , updatable = false)
    private String commercialId;


    @Column
    private String daycareName;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String description;

    @Column
    private String facilities;

    @Column
    @Email
    private String email;


    @Column(columnDefinition = "INT CHECK (capacity <= 100)")
    @Max(value = 100, message = "Capacity cannot be greater than 100")
    private Integer capacity;

    @Column
    private String location;

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "closing_time")
    private String closingTime;

//    @Column(columnDefinition = "DECIMAL(3,1) CHECK (rate >= 1 AND rate <= 5)")
    private Double rate;



    // relationship

    // the daycare with Stuff
    @OneToMany(mappedBy="daycare", cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Stuff> stuffList;

    // the daycare with Service
    @OneToMany(mappedBy="daycare", cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Package> packageList;

    // one daycare belonged to many ratings
    @OneToMany(mappedBy = "daycare", cascade = CascadeType.ALL)
    private Set<Rating> rates;


    // relationship for security
    @OneToOne
    @JsonIgnore
    @MapsId
    private MyUser myUser;

}