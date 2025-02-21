package org.gordeser.user_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gordeser.user_service.dto.SkillDto;
import org.gordeser.user_service.service.SkillService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
@Slf4j
public class SkillController {
    private final SkillService skillService;

    @PostMapping
    public SkillDto createSkill(@Valid @RequestBody SkillDto skill) {
        log.info("Create skill: {}", skill);
        return skillService.createSkill(skill);
    }

    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable("id") Long skillId) {
        log.info("Delete skill by id: {}", skillId);
        skillService.deleteSkill(skillId);
        return "OK";
    }
}
