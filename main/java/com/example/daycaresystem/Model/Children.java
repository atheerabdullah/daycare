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
public class Children {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String childName;
    private Integer age;
    private String gender;
    private String disable;

    //    Relation M-O i try it in post man and it is working
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="parent_id",referencedColumnName = "my_user_id")
    private Parent parent;



}
