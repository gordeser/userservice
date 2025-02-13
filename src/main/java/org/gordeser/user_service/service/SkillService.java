package org.gordeser.user_service.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.user_service.dto.SkillDto;
import org.gordeser.user_service.entitiy.Skill;
import org.gordeser.user_service.exception.DataValidationException;
import org.gordeser.user_service.repository.SkillRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public Skill createSkill(SkillDto skill) {

        if (skillRepository.findByTitle(skill.getTitle()).isPresent()) {
            throw new DataValidationException("Skill with that title already exists.");
        }

        Skill newSkill = Skill.builder()
                .title(skill.getTitle())
                .build();

        return skillRepository.save(newSkill);
    }

    public void deleteSkill(Long skillId) {
        Skill skillToDelete = skillRepository.findById(skillId).orElseThrow(
                () -> new DataValidationException("Skill with that ID does not exists")
        );

        skillRepository.delete(skillToDelete);
    }
}
