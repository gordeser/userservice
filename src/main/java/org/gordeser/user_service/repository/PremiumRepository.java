package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiumRepository extends JpaRepository<Premium, Long> {
}