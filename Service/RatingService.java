package com.example.daycaresystem.Service;


import com.example.daycaresystem.ApiException.ApiException;
import com.example.daycaresystem.DTO.RatingDTO;
import com.example.daycaresystem.Model.Daycare;
import com.example.daycaresystem.Model.Rating;
import com.example.daycaresystem.Repository.DaycareRepository;
import com.example.daycaresystem.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RatingService {


    private final RatingRepository ratingRepository;
    private final DaycareRepository daycareRepository;


    // CRUD point


    public RatingDTO addCommentToDaycare(Integer daycareId, RatingDTO ratingDTO) {
        Daycare daycare = daycareRepository.findById(daycareId)
                .orElseThrow(() -> new ApiException("Daycare not found with id: " + daycareId));

        // Create a new Rate object and set its properties
        Rating rating = new Rating();
        rating.setCalculateRating(ratingDTO.getRate());
        rating.setComments(ratingDTO.getComments());

        // Add the new Rate object to the Daycare's set of Rates
        daycare.getRates();

        // Recalculate the average rating
        daycare.setRate(calculateAverageRating(daycare.getRates()));

        // Save the updated daycare
        daycareRepository.save(daycare);

        // Return the saved Rate as a RatingDTO
        RatingDTO savedRatingDTO = new RatingDTO();
        savedRatingDTO.setId(rating.getId());
        savedRatingDTO.setRate(rating.getCalculateRating());
        savedRatingDTO.setComments(rating.getComments());
        return savedRatingDTO;
    }


    private Double calculateAverageRating(Set<Rating> rates) {
        if (rates == null || rates.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (Rating rate : rates) {
            sum += rate.getCalculateRating();
        }
        return (double) sum / rates.size();
    }
}

