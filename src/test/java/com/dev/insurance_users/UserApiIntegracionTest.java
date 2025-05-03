package com.dev.insurance_users;

import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest(classes = InsuranceUsersApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void findAllUsersTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void findUserByNonExistentDniTest() throws Exception {
        mockMvc.perform(get("/users/dni/87654321B"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findUserByDniTest() throws Exception {
        mockMvc.perform(get("/users/dni/12345678A"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserByEmailTest() throws Exception {
        mockMvc.perform(get("/users/email/juan@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("juan@example.com"));
    }

    @Test
    public void findUserByNonExistentEmailTest() throws Exception {
        mockMvc.perform(get("/users/email/ju31an@example.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findUserByIdTest() throws Exception {
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("maria@example.com"))
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void findNonExistentUserByIdTest() throws Exception {
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findInvalidUserByIdTest() throws Exception {
        mockMvc.perform(get("/users/0").contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/users/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void createUserTest() throws Exception {
        String newUserJson = """
                    {
                        "name": "Carlos",
                        "surname": "González",
                        "phone": "666666666",
                        "email": "carlosgonzalez@example.com",
                        "dni": "87654321O",
                        "password": "securePass456",
                        "city": "Sevilla",
                        "country": "Spain",
                        "address": "Avenida Principal 45",
                        "dateOfBirth": "1985-05-15"
                    }
                """;
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(newUserJson))
                .andExpect(status().isCreated());
    }

    @Test // TODO : FIX ERR 409
    public void createUserDuplicateKeyTest() throws Exception {
        String existingUserJson = """
                    {
                        "name": "Juan",
                        "surname": "Pérez",
                        "phone": "123456789",
                        "email": "juan@example.com",
                        "dni": "12345678A",
                        "password": "securePass123",
                        "city": "Madrid",
                        "country": "Spain",
                        "address": "Calle Falsa 123",
                        "dateOfBirth": "1990-01-01",
                        "vehicles": [
                        {
                                  "id": 1,
                                  "userThirdId": 101,
                                  "matricula": "ABC123",
                                  "km": 25000,
                                  "marca": "Toyota",
                                  "aseguradora": "Mapfre",
                                  "color": "Rojo",
                                  "fechaFabricacion": "2018-06-15",
                                  "dateOfRegistration": "2018-07-01",
                                  "dateOfLastUpdate": "2025-05-03"
                                }
                        ]
                    }
                """;
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(existingUserJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateUserTest() throws Exception {
        String updatedUserJson = """
                    {
                        "name": "Carlos",
                        "surname": "González",
                        "phone": "654321987",
                        "email": "carlos.gonzalez@example.com",
                        "dni": "87654321B",
                        "password": "securePass456",
                        "city": "Sevilla",
                        "country": "Spain",
                        "address": "Avenida Principal 45",
                        "dateOfBirth": "1985-05-15"
                    }
                """;
        mockMvc.perform(put("/users/2")
                        .contentType("application/json")
                        .content(updatedUserJson))
                .andExpect(status().isOk());
    }
}