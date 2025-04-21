package com.dev.insurance_users;


import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void findUserThirdByIdTest() throws Exception {
        mockMvc.perform(delete("/third_users/1"))
                .andExpect(status().isNoContent());
    }
    @Test // TODO: añadir 404 en el contract yml
    public void findUserThirdByIdNotFoundTest() throws Exception {
        mockMvc.perform(delete("/third_users/999"))
                .andExpect(status().isNotFound());
    }
    @Test // TODO:
    public void findUserThirdByInvalidIdTest() throws Exception {
        mockMvc.perform(delete("/third_users/invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveThirdUserTest() throws Exception {
        String newThirdUser = """
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
                """;

        mockMvc.perform(post("/third_users")
            .contentType("application/json")
            .content(newThirdUser))
            .andExpect(status().isCreated());
    }
    @Test // TODO: duplicated key test
    public void saveThirdUserDuplicateKeyTest() throws Exception {
        mockMvc.perform(post("/third_users"))
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
                    "dni": "12345678A",
                    "city": "Barcelona",
                    "country": "Spain",
                    "address": "Calle Nueva 456",
                    "dateOfBirth": "1990-01-01"
                }
                """;
        mockMvc.perform(put("/third_users/1")
                .contentType("application/json")
                .content(updatedThirdUser))
                .andExpect(status().isOk());
    }

}
