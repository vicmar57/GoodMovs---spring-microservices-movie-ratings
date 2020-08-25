package com.vicmar57.moviecatalogservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vicmar57.moviecatalogservice.models.CatalogItem;
import com.vicmar57.moviecatalogservice.models.Movie;
import com.vicmar57.moviecatalogservice.models.Rating;
import com.vicmar57.moviecatalogservice.models.UserRating;
import com.vicmar57.moviecatalogservice.services.MovieInfo;
import com.vicmar57.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired //somebody has a bean somewhere, of type RestTemplate. inject me that bean. autowire is a cosumer.
    //injection by type
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;
//    @Autowired
//    private WebClient.Builder WebClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userid){
        //get all rated movie ids
        UserRating ratings = userRatingInfo.getUserRating(userid);

        return ratings.getUserRating().stream().map(rating -> {
//            Movie movie = WebClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();

                //for each movieId call movie-info-service and get details
                Movie movie = movieInfo.getMovieInfo(rating);
                //put them all together
                return new CatalogItem(movie.getName(), movie.getOverview(), rating.getRating());
        })
        .collect(Collectors.toList());
    }

    }
