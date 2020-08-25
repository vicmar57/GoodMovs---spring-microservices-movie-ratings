package com.vicmar57.ratingsdataservice.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
     * This class will represent our user-ratings and its attributes:
     * - usr_id
     * - movie_id
     * - user_rating
     *
     * @author Victor Martinov
     */
    @Entity
    @Table(name = "users_ratings")   // the table in the database that will contain our phones data
    @EntityListeners(AuditingEntityListener.class)
    public class RatingsTableRow {

        /**
         * The attributes of the phone
         */
        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "user_id", nullable = false)
        private int id;    // Each phone will be given an auto-generated unique identifier when stored

        @Column(name = "movie_id", nullable = false)
        private int movieId;    // We will also save the name of the phone

        @Column(name = "rating", nullable = false)
        private int userRating;    // We will also save the operating system running the phone

        /**
         * Our getters and setters for the attributes above
         */
        public long getId() {
            return id;
        }

        public void setId(int value) {
            this.id = value;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int value) {
            this.movieId = value;
        }

        public int getUserRating() {
            return userRating;
        }

        public void setUserRating(int value) {
            this.userRating = value;
        }
}
