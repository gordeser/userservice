package org.gordeser.user_service.service;

import org.gordeser.user_service.dto.SkillDto;
import org.gordeser.user_service.entitiy.Skill;
import org.gordeser.user_service.exception.DataValidationException;
import org.gordeser.user_service.repository.SkillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;


    @InjectMocks
    private SkillService skillService;


    private Skill skill;
    private SkillDto skillDto;

    @BeforeEach
    void setUp() {
        skill = Skill.builder().id(1L).title("TestSkill").build();
        skillDto = SkillDto.builder().title("TestSkill").build();
    }

    @Test
    void createSkillTest() {
        // Arrange
        Mockito.when(skillRepository.findByTitle(skillDto.getTitle())).thenReturn(Optional.empty());
        Mockito.when(skillRepository.save(Mockito.any(Skill.class))).thenReturn(skill);

        // Act
        Skill createdSkill = skillService.createSkill(skillDto);

        // Assert
        Assertions.assertNotNull(createdSkill);
        Assertions.assertEquals(skillDto.getTitle(), createdSkill.getTitle());
        Mockito.verify(skillRepository, Mockito.times(1)).findByTitle(skillDto.getTitle());
        Mockito.verify(skillRepository, Mockito.times(1)).save(Mockito.any(Skill.class));
    }

    @Test
    void createSkillTitleAlreadyExistsTest() {
        // Arrange
        Skill anotherSkillWithTitle = Skill.builder().id(2L).title("TestSkill").build();
        Mockito.when(skillRepository.findByTitle(skillDto.getTitle())).thenReturn(Optional.of(anotherSkillWithTitle));

        // Act & Assert
        var exception = Assertions.assertThrows(DataValidationException.class, () -> skillService.createSkill(skillDto));
        Assertions.assertEquals("Skill with that title already exists.", exception.getMessage());
        Mockito.verify(skillRepository, Mockito.times(1)).findByTitle(skillDto.getTitle());
        Mockito.verify(skillRepository, Mockito.never()).save(Mockito.any(Skill.class));
    }
}
