package com.dev.insurance_users;

import com.dev.insurance_users.infrastructure.repository.jpa.VehicleJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleThirdJpaRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DirtiesContext
@SpringBootTest(classes = InsuranceUsersApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VehicleApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VehicleJpaRepository vehicleJpaRepository;

    @Test
    public void deleteVehicleTest() throws Exception {
        mockMvc.perform(delete("/vehicles/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void findVehicleByIdTest() throws Exception {
        mockMvc.perform(get("/vehicles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.marca").value("Toyota"));
    }
    @Test
    public void findNonExistentVehicleByIdTest() throws Exception {
        mockMvc.perform(get("/vehicles/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByMatriculaTest() throws Exception {
        mockMvc.perform(get("/vehicles/matricula/1234ABC"))
                .andExpect(status().isOk());
    }
    @Test
    public void findByNonExistentMatriculaTest() throws Exception {
        mockMvc.perform(get("/vehicles/matricula/9999XYZ"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllVehiclesTest() throws Exception {
        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getVehiclesByUserIdTest() throws Exception {
        mockMvc.perform(get("/vehicles/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    @Test
    public void getVehiclesByNonExistentUserIdTest() throws Exception {
        mockMvc.perform(get("/vehicles/user/11111"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void saveVehicleTest() throws Exception {
        String newVehicleJson = """
            {       
                "marca": "Honda",
                "color": "Civic",
                "fechaFabricacion": "2022-01-01",
                "matricula": "1234XYZ",
                "km": 0,
                "userId": 1
            }
            """;
        mockMvc.perform(post("/vehicles")
                .contentType("application/json")
                .content(newVehicleJson))
                .andExpect(status().isCreated());
    }
    @Test //TODO: FIX ERR 409(500)
    public void saveVehicleDuplicateKeyTest() throws Exception {
        String existingVehicleJson = """
                    {
                        "marca": "Ford",
                        "color": "Azul",
                        "fechaFabricacion": "2020-01-01",
                        "matricula": "UNIQUE123",
                        "km": 10000,
                        "userId": 1
                    }
                """;
    }

    @Test
    public void updateVehicleTest() throws Exception {
        String updatedVehicleJson = """
            {
                "brand": "Honda Updated",
                "model": "Civic Updated",
                "year": 2023,
                "licensePlate": "5678ABC",
                "type": "CAR",
                "userId": 1
            }
        """;
        mockMvc.perform(put("/vehicles/1")
                .contentType("application/json")
                .content(updatedVehicleJson))
                .andExpect(status().isOk());
    }

}