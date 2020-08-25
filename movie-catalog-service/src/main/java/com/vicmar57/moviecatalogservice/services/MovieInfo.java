package com.vicmar57.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vicmar57.moviecatalogservice.models.Movie;
import com.vicmar57.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

//externalizing the service for the hystrix proxy to work correctly
@Service // makes it a spring bean
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
    public Movie getMovieInfo(Rating rating) {
        return restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
    }

    public Movie getFallbackMovieInfo(Rating rating) {
        return new Movie("no movie id", "no title", "no summary");
    }
}
