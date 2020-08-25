package com.vicmar57.ratingsdataservice;

import com.vicmar57.ratingsdataservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.*;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
    @Autowired
    private movieDBrepo movieRepo;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void Client(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/allRatings")
    public List<RatingsTableRow> getAllRatings() {
        return movieRepo.findAll();
    }

    // GET method to fetch phone by Id
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<List<Rating>> getRatingsByMovieId(@PathVariable(value = "movieId") Integer movieId) {

//        // Prep work
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//
//        Transaction tx = session.beginTransaction();
//        SQLQuery query = session.createSQLQuery("SELECT * FROM users_ratings WHERE movie_id = 123");
//        List<Object[]> rows = query.list();
//        for(Object[] row : rows) {
//            System.out.println(row);
//        }

        String SQLQuery = "SELECT * FROM users_ratings WHERE movie_id = ?";
        List<Map<String, Object>> queryRes = jdbcTemplate.queryForList(SQLQuery, movieId);

        List <Rating> ratings = new ArrayList<>();
        queryRes.forEach(entry -> ratings.add(new Rating(
                        String.valueOf(entry.get("movie_id")),
                        (Integer) entry.get("rating")))
                );

        //List<Ratings> ratings = movieRepo.findAllById(Collections.singleton(userId));
                //.orElseThrow(() -> new Exception("ratings for user " + userId + " not found"));
        return ResponseEntity.ok().body(ratings);
    }

    @RequestMapping("users/{userId}")
    public ResponseEntity<UserRating> getAllUserRatings(@PathVariable("userId") Integer userId){
        String SQLQuery = "SELECT movie_id, rating FROM users_ratings WHERE user_id = ?";
        List<Map<String, Object>> movieRatings = jdbcTemplate.queryForList(SQLQuery, userId);

        List <Rating> ratings = new ArrayList<>();
        movieRatings.forEach(entry -> ratings.add(new Rating(
                String.valueOf(entry.get("movie_id")),
                (Integer) entry.get("rating"))));

        UserRating userRatings = new UserRating();
        userRatings.setUserRating(ratings);
        return ResponseEntity.ok().body(userRatings);
    }
}
