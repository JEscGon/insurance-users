package com.dev.insurance_users;


import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest(classes = InsuranceUsersApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserThirdApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserThirdJpaRepository userThirdJpaRepository;

    @Test
    public void deleteUserThirdTest() throws Exception {
        mockMvc.perform(delete("/third_users/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteUserThirdNotFoundTest() throws Exception {
        mockMvc.perform(delete("/third_users/999"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void findAllUserThirdTest() throws Exception {
        mockMvc.perform(get("/third_users"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserThirdByIdNotFoundTest() throws Exception {
        mockMvc.perform(delete("/third_users/999"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void findUserThirdByInvalidIdTest() throws Exception {
        mockMvc.perform(delete("/third_users/0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveThirdUserTest() throws Exception {
        String newThirdUser = """
        {
            "users": [
                {
                    "name": "Juan",
                    "surname": "Gomez",
                    "phone": "123456789",
                    "email": "juan.gomez@example.com",
                    "dni": "12345678A",
                    "city": "Madrid",
                    "country": "Spain",
                    "address": "Calle Falsa 123",
                    "dateOfBirth": "1990-01-01"
                }
            ]
        }
        """;
            mockMvc.perform(post("/third_users")
                            .contentType("application/json")
                            .content(newThirdUser))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void saveThirdUserDuplicateKeyTest() throws Exception {
        String existingThirdUser = """
        {
            "users": [
                {
                    "name": "Juan",
                    "surname": "Gomez",
                    "phone": "123456789",
                    "email": "juan.gomez@example.com",
                    "dni": "12345678A",
                    "city": "Madrid",
                    "country": "Spain",
                    "address": "Calle Falsa 123",
                    "dateOfBirth": "1990-01-01"
                }
            ]
        }
        """;
        mockMvc.perform(post("/third_users")
                        .contentType("application/json")
                        .content(existingThirdUser))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/third_users")
                        .contentType("application/json")
                        .content(existingThirdUser))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateThirdUserTest() throws Exception {
        String updatedThirdUser = """
                {
                    "name": "Juan Updated",
                    "surname": "Gomez Updated",
                    "phone": "987654321",
                    "email": "juan.updated@example.com",
                    "dni": "87654321F",
                    "city": "Barcelona",
                    "country": "Spain",
                    "address": "Calle Nueva 456",
                    "dateOfBirth": "1990-01-01",
                    "vehicles": [{
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
                                 }]
                }
                """;
        mockMvc.perform(put("/third_users/6")
                        .contentType("application/json")
                        .content(updatedThirdUser))
                .andExpect(status().isOk());
    }

}
