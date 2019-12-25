package com.vicmar57.ratingsdataservice;

import com.vicmar57.ratingsdataservice.models.Rating;
import com.vicmar57.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

    Random rand = new Random();

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating(String.valueOf(1+ rand.nextInt(950)), 4),
                new Rating(String.valueOf(1+ rand.nextInt(950)) , 3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
