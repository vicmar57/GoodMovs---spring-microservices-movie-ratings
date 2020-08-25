package com.vicmar57.movieinfoservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vicmar57.movieinfoservice.models.Movie;
import com.vicmar57.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
//@RequestMapping("/movies")
//public class MovieResource {
//
//    @RequestMapping("/{movieId}")
//     public Movie getMovieInfo(@PathVariable("movieId") String movieId){
//         return new Movie(movieId, "TEST NAME");
//     }
//}

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}") // get value from application.properties file
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}

