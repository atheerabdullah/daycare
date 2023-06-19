package com.example.daycaresystem.Controller;


import com.example.daycaresystem.DTO.RatingDTO;
import com.example.daycaresystem.Model.Rating;
import com.example.daycaresystem.Service.DayCareService;
import com.example.daycaresystem.Service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/Rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final DayCareService dayCareService;


    // crud endpoint


    //add comment and rate to daycare
    @PostMapping("/daycares/{daycareId}/comments")
    public ResponseEntity<RatingDTO> addCommentToDaycare(@PathVariable Integer daycareId, @RequestBody RatingDTO ratingDTO) {
        RatingDTO savedRatingDTO = ratingService.addCommentToDaycare(daycareId, ratingDTO);
        return ResponseEntity.ok(savedRatingDTO);
    }

}
