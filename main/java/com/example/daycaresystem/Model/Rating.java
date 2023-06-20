package com.example.daycaresystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double calculateRating;

    @Column
    private String comments;



    // one Rate have many daycare's
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_user_id")
    private Daycare daycare;


    // one booking have one rate
    @OneToOne
    @MapsId
    @JsonIgnore
    private Booking booking1;


//    //one rate many  daycare
//    @OneToMany(mappedBy = "Rating" , cascade=CascadeType.ALL)
//    private List<Daycare> daycares;


}
