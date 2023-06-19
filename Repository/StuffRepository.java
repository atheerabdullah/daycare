package com.example.daycaresystem.Repository;

import com.example.daycaresystem.Model.Daycare;
import com.example.daycaresystem.Model.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuffRepository extends JpaRepository<Stuff,Integer> {

    Stuff findStuffByid(Integer id);

    boolean existsStuffsByCivilRecord( Integer CivilRecord);

    List<Stuff> findStuffByDaycare_DaycareName(String daycareName);






}
