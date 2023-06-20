package com.example.daycaresystem.Repository;

import com.example.daycaresystem.DTO.RatingDTO;
import com.example.daycaresystem.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository <Rating , Integer> {

    RatingDTO findAllById(Integer id);

}
