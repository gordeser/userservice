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
        Mockito.when(skillRepository.existsByTitle(skillDto.getTitle())).thenReturn(Boolean.FALSE);
        Mockito.when(skillRepository.save(Mockito.any(Skill.class))).thenReturn(skill);

        // Act
        SkillDto createdSkill = skillService.createSkill(skillDto);

        // Assert
        Assertions.assertNotNull(createdSkill);
        Assertions.assertEquals(skillDto.getTitle(), createdSkill.getTitle());
        Mockito.verify(skillRepository, Mockito.times(1)).existsByTitle(skillDto.getTitle());
        Mockito.verify(skillRepository, Mockito.times(1)).save(Mockito.any(Skill.class));
    }

    @Test
    void createSkillTitleAlreadyExistsTest() {
        // Arrange
        Mockito.when(skillRepository.existsByTitle(skillDto.getTitle())).thenReturn(Boolean.TRUE);

        // Act & Assert
        Exception exception = Assertions.assertThrows(DataValidationException.class, () -> skillService.createSkill(skillDto));

        Assertions.assertEquals("Skill with title " + skillDto.getTitle() + " already exists.", exception.getMessage());
        Mockito.verify(skillRepository, Mockito.times(1)).existsByTitle(skillDto.getTitle());
        Mockito.verify(skillRepository, Mockito.never()).save(Mockito.any(Skill.class));
    }
}
