package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void findById_UserExists_ReturnsUser() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(2L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }
    @Test
    void findById_UserDoesNotExist_ReturnsEmpty() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(99L);

        assertTrue(result.isEmpty());
    }
    @Test
    void findAll_ReturnsUserList() {
        List<User> users = Arrays.asList(
            createUser(1L, "user1@test.com"),
            createUser(2L, "user2@test.com")
        );

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }
    @Test
    void getUserByDni_UserExists_ReturnsUser() {
        User user = createUser(1L, "user@test.com", "John", "Doe", "123456789",
                "12345678A", "password", "Madrid", "Spain", "Calle Mayor 1",
                LocalDate.of(1990, 1, 1), LocalDate.now(), LocalDate.now(), null);

        when(userRepository.findByDni("12345678A")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByDni("12345678A");

        assertTrue(result.isPresent());
        assertEquals("12345678A", result.get().getDni());
    }
    @Test
    void getUserByDni_UserDoesNotExist_ReturnsEmpty() {
        when(userRepository.findByDni("99999999Z")).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserByDni("99999999Z");

        assertTrue(result.isEmpty());
    }
    @Test
    void getUserByEmail_UserExists_ReturnsUser() {
        User user = createUser(1L, "user@test.com");

        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByEmail("user@test.com");

        assertTrue(result.isPresent());
        assertEquals("user@test.com", result.get().getEmail());
    }
    @Test
    void getUserByEmail_UserDoesNotExist_ReturnsEmpty() {
        when(userRepository.findByEmail("nonexistent@test.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserByEmail("nonexistent@test.com");

        assertTrue(result.isEmpty());
    }
    @Test
    void save_ShouldCallRepositorySave() {
        User user = createUser(1L, "user@test.com");

        userService.save(user);

        org.mockito.Mockito.verify(userRepository).save(user);
    }
    @Test
    void deleteUserById_ShouldCallRepositoryDeleteById() {
        Long userId = 1L;

        userService.deleteUserById(userId);

        org.mockito.Mockito.verify(userRepository).deleteById(userId);
    }

    private User createUser(long id, String email, String name, String surname, String phone,
                            String dni, String password, String city, String country, String address,
                            LocalDate dateOfBirth, LocalDate dateOfRegistration, LocalDate dateOfLastUpdate,
                            List<Vehicle> vehicles) {

        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setDni(dni);
        user.setPassword(password);
        user.setCity(city);
        user.setCountry(country);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setDateOfRegistration(dateOfRegistration);
        user.setDateOfLastUpdate(dateOfLastUpdate);
        user.setVehicles(vehicles);

        return user;
    }

    private User createUser(long id, String email) {
        return createUser(id, email, "DefaultName", "DefaultSurname", "000000000",
                "00000000X", "defaultPassword", "DefaultCity", "DefaultCountry", "DefaultAddress",
                LocalDate.now().minusYears(30), LocalDate.now(), LocalDate.now(), null);
    }

}


/**
createUser(1L, "user1@test.com", "John", "Doe", "123456789",
        "12345678A", "password", "Madrid", "Spain", "Calle Mayor 1",
        LocalDate.of(1990, 1, 1), LocalDate.now(), LocalDate.now(), null),

createUser(2L, "user2@test.com", "Jane", "Doe", "987654321",
        "87654321B", "password", "Barcelona", "Spain", "Calle Nueva 2",
        LocalDate.of(1995, 5, 5), LocalDate.now(), LocalDate.now(), null)
**/