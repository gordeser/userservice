package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}