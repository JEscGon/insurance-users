package com.dev.insurance_users.infrastructure.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {
    @Test
    void testUserEntity() {
        // Arrange
        Long id = 1L;
        String name = "Juan";
        String surname = "Pérez";
        String phone = "123456789";
        String email = "juan@example.com";
        String dni = "12345678A";
        String password = "password123";
        String city = "Madrid";
        String country = "España";
        String address = "Calle Principal 123";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDate dateOfRegistration = LocalDate.now();
        LocalDate dateOfLastUpdate = LocalDate.now();

        // Act
        UserEntity user = new UserEntity();
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
        user.setVehicles(new ArrayList<>());

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(phone, user.getPhone());
        assertEquals(email, user.getEmail());
        assertEquals(dni, user.getDni());
        assertEquals(password, user.getPassword());
        assertEquals(city, user.getCity());
        assertEquals(country, user.getCountry());
        assertEquals(address, user.getAddress());
        assertEquals(dateOfBirth, user.getDateOfBirth());
        assertEquals(dateOfRegistration, user.getDateOfRegistration());
        assertEquals(dateOfLastUpdate, user.getDateOfLastUpdate());
        assertNotNull(user.getVehicles());
        assertTrue(user.getVehicles().isEmpty());
    }

}