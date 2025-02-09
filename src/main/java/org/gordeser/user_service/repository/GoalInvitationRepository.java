package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.GoalInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalInvitationRepository extends JpaRepository<GoalInvitation, Long> {
}