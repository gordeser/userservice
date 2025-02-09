package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}