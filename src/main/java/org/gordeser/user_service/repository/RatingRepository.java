package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}