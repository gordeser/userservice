package org.gordeser.user_service.service;

import org.gordeser.user_service.dto.CountryDto;
import org.gordeser.user_service.dto.UserDto;
import org.gordeser.user_service.entitiy.Country;
import org.gordeser.user_service.entitiy.User;
import org.gordeser.user_service.repository.CountryRepository;
import org.gordeser.user_service.repository.UserRepository;
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
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    private Country country;


    @BeforeEach
    void setUp() {
        country = Country.builder()
                .id(1L)
                .name("testCountry")
                .build();

        user = User.builder()
                .id(1L)
                .username("testUsername")
                .email("test@email.com")
                .phone("123456789")
                .password("testPassword")
                .aboutMe("testAboutMe")
                .country(country)
                .city("testCity")
                .build();
    }

    @Test
    void createUserTest() {
        CountryDto countryDto = CountryDto.builder()
                .name("testCountry")
                .build();

        UserDto userDto = UserDto.builder()
                .username("testUsername")
                .email("test@email.com")
                .phone("123456789")
                .password("testPassword")
                .aboutMe("testAboutMe")
                .country(countryDto)
                .city("testCity")
                .build();

        Mockito.when(userRepository.findByUsername(userDto.getUsername())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByPhone(userDto.getPhone())).thenReturn(Optional.empty());
        Mockito.when(countryRepository.findByName(countryDto.getName())).thenReturn(Optional.of(country));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(userDto);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
        Assertions.assertEquals(userDto.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(userDto.getEmail(), createdUser.getEmail());
        Assertions.assertEquals(userDto.getPhone(), createdUser.getPhone());
        Assertions.assertEquals(userDto.getPassword(), createdUser.getPassword());
        Assertions.assertEquals(userDto.getAboutMe(), createdUser.getAboutMe());
        Assertions.assertEquals(countryDto.getName(), createdUser.getCountry().getName());
        Assertions.assertEquals(userDto.getCity(), createdUser.getCity());
    }

    @Test
    void createUserUsernameExistsTest() {
        CountryDto countryDto = CountryDto.builder()
                .name("testCountry")
                .build();

        UserDto userDto = UserDto.builder()
                .username("testUsername")
                .email("test1@email.com")
                .phone("0123456789")
                .password("testPassword")
                .aboutMe("testAboutMe")
                .country(countryDto)
                .city("testCity")
                .build();

        Mockito.when(userRepository.findByUsername(userDto.getUsername())).thenReturn(Optional.of(user));


        var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.createUser(userDto));
        Assertions.assertEquals("User with that username already exists", exception.getMessage());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(userDto.getUsername());
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }

    @Test
    void createUserEmailExistsTest() {
        CountryDto countryDto = CountryDto.builder()
                .name("testCountry")
                .build();

        UserDto userDto = UserDto.builder()
                .username("test1Username")
                .email("test@email.com")
                .phone("0123456789")
                .password("testPassword")
                .aboutMe("testAboutMe")
                .country(countryDto)
                .city("testCity")
                .build();

        Mockito.when(userRepository.findByUsername(userDto.getUsername())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));


        var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.createUser(userDto));
        Assertions.assertEquals("User with that email already exists", exception.getMessage());
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(userDto.getEmail());
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }

    @Test
    void createUserPhoneExistsTest() {
        CountryDto countryDto = CountryDto.builder()
                .name("testCountry")
                .build();

        UserDto userDto = UserDto.builder()
                .username("test1Username")
                .email("test1@email.com")
                .phone("123456789")
                .password("testPassword")
                .aboutMe("testAboutMe")
                .country(countryDto)
                .city("testCity")
                .build();

        Mockito.when(userRepository.findByUsername(userDto.getUsername())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByPhone(userDto.getPhone())).thenReturn(Optional.of(user));


        var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.createUser(userDto));
        Assertions.assertEquals("User with that phone already exists", exception.getMessage());
        Mockito.verify(userRepository, Mockito.times(1)).findByPhone(userDto.getPhone());
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }

    @Test
    void createUserCountryDoesNotExistTest() {
        CountryDto countryDto = CountryDto.builder()
                .name("test1Country")
                .build();

        UserDto userDto = UserDto.builder()
                .username("test1Username")
                .email("test1@email.com")
                .phone("0123456789")
                .password("testPassword")
                .aboutMe("testAboutMe")
                .country(countryDto)
                .city("testCity")
                .build();

        Mockito.when(userRepository.findByUsername(userDto.getUsername())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByPhone(userDto.getPhone())).thenReturn(Optional.empty());
        Mockito.when(countryRepository.findByName(countryDto.getName())).thenReturn(Optional.empty());


        var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.createUser(userDto));
        Assertions.assertEquals("Country with that name does not exist", exception.getMessage());
        Mockito.verify(countryRepository, Mockito.times(1)).findByName(countryDto.getName());
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }

    @Test
    void getUserByIdTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        User returnedUser = userService.getUserById(1L);

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Assertions.assertNotNull(returnedUser);
        Assertions.assertEquals(user.getId(), returnedUser.getId());
    }

    @Test
    void getUserByIdUserDoesNotExistTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
    }

}
