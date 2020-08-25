package com.vicmar57.ratingsdataservice.models;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface movieDBrepo.
 *
 * @author Victor Martinov
 */
public interface movieDBrepo extends JpaRepository<RatingsTableRow, Integer> {

}