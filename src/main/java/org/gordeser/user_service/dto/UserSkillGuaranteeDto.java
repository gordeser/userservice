package org.gordeser.user_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSkillGuaranteeDto {
    private Long id;
    private Long userId;
    private Long skillId;
    private Long guarantorId;
}
