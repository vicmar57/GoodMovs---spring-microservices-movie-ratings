package com.vicmar57.moviecatalogservice.models;

import java.util.List;

public class UserRating {

    private List<Rating> userRating;
    private String userId;

    public UserRating() {
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
}
