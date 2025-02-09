package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}