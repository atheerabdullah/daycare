package com.example.daycaresystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

@Table(name = "packageObj")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String packageType;

    @Column(name = "package_duration")
    private String packageDuration;

    @Column(name = "package_price")
    private Double packagePrice;


    @Column(name = "description")
    private String description;


    // add package to daycare
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daycare_id", referencedColumnName = "my_user_id")
    @JsonIgnore
    private Daycare daycare;

    // assign the stuff to package
    @ManyToMany
    @JsonIgnore
    private Set<Stuff> stuffs;

    // service with package > many packages have one booking
    @OneToMany(mappedBy = "packageObj")
    private Set<Booking> bookings;


}
