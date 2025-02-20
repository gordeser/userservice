package org.gordeser.user_service.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.user_service.dto.UserDto;
import org.gordeser.user_service.entitiy.Country;
import org.gordeser.user_service.entitiy.User;
import org.gordeser.user_service.repository.CountryRepository;
import org.gordeser.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    public User createUser(UserDto user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User with that username already exists");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with that email already exists");
        }

        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            throw new RuntimeException("User with that phone already exists");
        }

        Country userCountry = countryRepository.findByName(user.getCountry().getName())
                .orElseThrow(() -> new RuntimeException("Country with that name does not exist"));

        User newUser = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .aboutMe(user.getAboutMe())
                .country(userCountry)
                .city(user.getCity())
                .build();

        return userRepository.save(newUser);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with that id does not exist"));
    }

}
