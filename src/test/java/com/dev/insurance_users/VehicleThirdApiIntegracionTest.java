package com.dev.insurance_users;

import com.dev.insurance_users.infrastructure.repository.jpa.VehicleThirdJpaRepository;
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
    public void saveThirdVehicleTest() throws Exception {
        String newThirdVehicle = """
                {
                    "userThirdId": 1,
                    "matricula": "12334ABC",
                    "aseguradora": "Mapfre",
                    "km": 10000,
                    "fechaFabricacion": "2020-01-01",
                    "marca": "Toyota",
                    "modelo": "Corolla",
                    "color": "Rojo"
                }
                """;
        mockMvc.perform(post("/third_vehicles")
                        .contentType("application/json")
                        .content(newThirdVehicle))
                .andExpect(status().isCreated());
    }
    @Test //TODO : FIX ERR 409 // (500)
    public void saveThirdVehicleDuplicateKeyTest() throws Exception {
        String newThirdVehicle = """
                {
                    "userThirdId": 7,
                    "matricula": "7777GGG",
                    "aseguradora": "Mutua Madrileña",
                    "km": 40000,
                    "fechaFabricacion": "2018-09-12",
                    "marca": "BMW",
                    "modelo": "Serie 3",
                    "color": "Azul"
                }
                """;
        mockMvc.perform(post("/third_vehicles")
                        .contentType("application/json")
                        .content(newThirdVehicle))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateThirdVehicleTest() throws Exception {
        String json = """
                {
                    "matricula": "1234ABC",
                    "marca": "Toyota",
                    "modelo": "Corolla",
                    "color": "Rojo"
                }
                """;
        mockMvc.perform(put("/third_vehicles/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk());
    }

}
