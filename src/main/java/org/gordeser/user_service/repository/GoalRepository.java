package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}