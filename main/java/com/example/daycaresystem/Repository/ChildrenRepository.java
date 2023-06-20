package com.example.daycaresystem.Repository;

import com.example.daycaresystem.Model.Children;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildrenRepository extends JpaRepository <Children , Integer> {
    Children  findChildernById(Integer id);

    Children findChildernsByParentEmail(String email);

}
