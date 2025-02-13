package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.MentorshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorshipRequestRepository extends JpaRepository<MentorshipRequest, Long> {
}