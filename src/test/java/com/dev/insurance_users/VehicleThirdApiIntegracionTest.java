package com.dev.insurance_users;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest(classes = InsuranceUsersApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VehicleThirdApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VehicleThirdJpaRepository vehicleThirdJpaRepository;

    @Test
    public void deleteVehicleThirdByIdTest() throws Exception {
        mockMvc.perform(delete("/third_vehicles/2"))
                .andExpect(status().isNoContent());
    }
    @Test
    public void deleteVehicleThirdByIdNotFoundTest() throws Exception {
        mockMvc.perform(delete("/third_vehicles/999"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void findThirdVehicleByMatriculaTest() throws Exception {
        mockMvc.perform(get("/third_vehicles/matricula/1111AAA"))
                .andExpect(status().isOk());
    }
    @Test
    public void findThirdVehicleByNonExistentMatriculaTest() throws Exception {
        mockMvc.perform(get("/third_vehicles/matricula/9999XYZ"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllThirdVehiclesTest() throws Exception {
        mockMvc.perform(get("/third_vehicles"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveThirdVehiclesTest() throws Exception {
        String newThirdVehicles = """
        {
            "vehicles": [
                {
                    "matricula": "23333",
                    "km": 120000,
                    "marca": "Ford",
                    "aseguradora": "AXA",
                    "color": "Azul",
                    "fechaFabricacion": "2018-06-15",
                    "userThirdId": 2
                },
                {
                    "matricula": "444444",
                    "km": 80000,
                    "marca": "Honda",
                    "aseguradora": "Allianz",
                    "color": "Negro",
                    "fechaFabricacion": "2019-09-20",
                    "userThirdId": 3
                }
            ]
        }
        """;
        mockMvc.perform(post("/third_vehicles")
                        .contentType("application/json")
                        .content(newThirdVehicles))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").isNumber());
    }


}
