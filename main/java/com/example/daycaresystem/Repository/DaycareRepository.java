package com.example.daycaresystem.Repository;


import com.example.daycaresystem.DTO.DaycareDTO;
import com.example.daycaresystem.DTO.ShowDTO;
import com.example.daycaresystem.Model.Daycare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;


@Repository
public interface DaycareRepository extends JpaRepository<Daycare, Integer> {

    Daycare findDaycareById(Integer id);

    boolean existsByCommercialId(String commercialId);

    //find daycare by name
    Daycare findDaycareByDaycareName(String name);

    List<Daycare> findByFacilitiesContaining(String facilities);


}
