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
@Getter
@Setter
@Entity
@Table(name = "stuff")
public class Stuff {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stuff_sequence")
    @SequenceGenerator(name = "stuff_sequence", sequenceName = "stuff_sequence", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;


    @Column(unique = true, updatable = false, nullable = false)
    private Integer civilRecord;

    @Column
    private String stuffName;


    @Column(columnDefinition = "VARCHAR(20) CHECK (phone LIKE '+996%')")
    private String phone;

    @Column
    private String position;

    @Column
    private String qualifications;

    // add stuff to daycare
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daycare_id", referencedColumnName = "my_user_id")
    @JsonIgnore
    private Daycare daycare;

    // assign the stuff to package
    @ManyToMany(mappedBy = "stuffs")
    @JsonIgnore
    private Set<Package> packages;


}
