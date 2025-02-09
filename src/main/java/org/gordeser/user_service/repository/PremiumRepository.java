package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Premium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PremiumRepository extends JpaRepository<Premium, Long> {
}