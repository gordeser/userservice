package org.gordeser.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    private Long id;
    @NotBlank
    @Size(min = 1, max = 256)
    private String title;
    private List<UserSkillGuaranteeDto> guaranteeDtoList;
}
