package com.example.daycaresystem.Repository;

import com.example.daycaresystem.Model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent , Integer> {

    Parent findParentById(Integer id);

}
