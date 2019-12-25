package com.vicmar57.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vicmar57.moviecatalogservice.models.Rating;
import com.vicmar57.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatingInfo")
    public UserRating getUserRating(@PathVariable("userId") String userid) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userid, UserRating.class);
    }

    public UserRating getFallbackUserRatingInfo(@PathVariable("userId") String userid) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userid);
        userRating.setUserRating(Arrays.asList(
                new Rating("0", 0)
        ));
        return userRating;
    }
}
