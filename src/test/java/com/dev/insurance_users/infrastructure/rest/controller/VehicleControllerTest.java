package com.dev.insurance_users.infrastructure.rest.controller;


import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.service.VehicleService;
import com.dev.insurance_users.generated.model.VehicleDto;
import com.dev.insurance_users.infrastructure.rest.mapper.VehicleDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;

    @Mock
    private VehicleDtoMapper vehicleDtoMapper;

    @InjectMocks
    private VehicleController vehicleController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllVehicles_ShouldReturnVehiclesList() throws Exception {
        // Given
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1L);
        vehicle1.setMatricula("1234ABC");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2L);
        vehicle2.setMatricula("5678XYZ");

        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);

        VehicleDto dto1 = new VehicleDto();
        dto1.setId(1);
        dto1.setMatricula("1234ABC");

        VehicleDto dto2 = new VehicleDto();
        dto2.setId(2);
        dto2.setMatricula("5678XYZ");

        when(vehicleService.findAll()).thenReturn(vehicles);
        when(vehicleDtoMapper.fromDomainToDto(vehicle1)).thenReturn(dto1);
        when(vehicleDtoMapper.fromDomainToDto(vehicle2)).thenReturn(dto2);

        // When & Then
        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].matricula", is("1234ABC")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].matricula", is("5678XYZ")));
    }

    @Test
    void getVehicleById_WhenVehicleExists_ShouldReturnVehicle() throws Exception {
        // Given
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setMatricula("1234ABC");

        VehicleDto dto = new VehicleDto();
        dto.setId(1);
        dto.setMatricula("1234ABC");

        when(vehicleService.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(vehicleDtoMapper.fromDomainToDto(vehicle)).thenReturn(dto);

        // When & Then
        mockMvc.perform(get("/vehicles/{id}", vehicleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.matricula", is("1234ABC")));
    }

    @Test
    void getVehicleById_WhenVehicleDoesNotExist_ShouldReturnNotFound() throws Exception {
        // Given
        Long vehicleId = 99L;
        when(vehicleService.findById(vehicleId)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/vehicles/{id}", vehicleId))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveVehicle_ShouldReturnCreated() throws Exception {
        // Given
        VehicleDto dto = new VehicleDto();
        dto.setMatricula("1234ABC");

        Vehicle vehicle = new Vehicle();
        vehicle.setMatricula("1234ABC");

        when(vehicleDtoMapper.fromDtoToDomain(any(VehicleDto.class))).thenReturn(vehicle);
        doNothing().when(vehicleService).save(vehicle);

        // When & Then
        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateVehicle_ShouldReturnOk() throws Exception {
        // Given
        Long vehicleId = 1L;
        VehicleDto dto = new VehicleDto();
        dto.setId(1);
        dto.setMatricula("1234ABC");

        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setMatricula("1234ABC");

        when(vehicleDtoMapper.fromDtoToDomain(any(VehicleDto.class))).thenReturn(vehicle);
        doNothing().when(vehicleService).save(vehicle);

        // When & Then
        mockMvc.perform(put("/vehicles/{id}", vehicleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteVehicleById_ShouldReturnOk() throws Exception {
        // Given
        Long vehicleId = 1L;
        doNothing().when(vehicleService).deleteVehicleById(vehicleId);

        // When & Then
        mockMvc.perform(delete("/vehicles/{id}", vehicleId))
                .andExpect(status().isOk());

        verify(vehicleService, times(1)).deleteVehicleById(vehicleId);
    }

    @Test
    void findByMatricula_WhenVehicleExists_ShouldReturnVehicle() throws Exception {
        // Given
        String matricula = "1234ABC";
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setMatricula(matricula);

        VehicleDto dto = new VehicleDto();
        dto.setId(1);
        dto.setMatricula(matricula);

        when(vehicleService.findByMatricula(matricula)).thenReturn(Optional.of(vehicle));
        when(vehicleDtoMapper.fromDomainToDto(vehicle)).thenReturn(dto);

        // When & Then
        mockMvc.perform(get("/vehicles/matricula/{matricula}", matricula))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.matricula", is(matricula)));
    }

    @Test
    void findByMatricula_WhenVehicleDoesNotExist_ShouldReturnNotFound() throws Exception {
        // Given
        String matricula = "9999ZZZ";
        when(vehicleService.findByMatricula(matricula)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/vehicles/matricula/{matricula}", matricula))
                .andExpect(status().isNotFound());
    }
}