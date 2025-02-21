package org.gordeser.user_service.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.user_service.dto.SkillDto;
import org.gordeser.user_service.entitiy.Skill;
import org.gordeser.user_service.entitiy.UserSkillGuarantee;
import org.gordeser.user_service.exception.DataValidationException;
import org.gordeser.user_service.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillDto createSkill(SkillDto skillDto) {
        if (skillRepository.existsByTitle(skillDto.getTitle())) {
            throw new DataValidationException("Skill with title " + skillDto.getTitle() + " already exists.");
        }

        Skill newSkill = Skill.builder()
                .title(skillDto.getTitle())
                .build();

        Skill savedSkill = skillRepository.save(newSkill);

        return SkillDto.builder()
                .id(savedSkill.getId())
                .title(savedSkill.getTitle())
                .build();
    }

    public void deleteSkill(Long skillId) {
        Skill skillToDelete = skillRepository.findById(skillId).orElseThrow(
                () -> new DataValidationException("Skill with that ID does not exists")
        );

        skillRepository.delete(skillToDelete);
    }
}
