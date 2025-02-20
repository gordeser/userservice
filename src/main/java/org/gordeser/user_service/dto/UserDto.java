package org.gordeser.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link org.gordeser.user_service.entitiy.User}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @Size(min = 1, max = 256)
    @NotBlank
    private String username;
    @Size(min = 1, max = 256)
    @Email
    @NotBlank
    private String email;
    @Size(min = 1, max = 256)
    private String phone;
    @Size(min = 1, max = 256)
    @NotBlank
    private String password;
    @Size(max = 4096)
    private String aboutMe;
    private CountryDto country;
    @Size(max = 256)
    private String city;
}