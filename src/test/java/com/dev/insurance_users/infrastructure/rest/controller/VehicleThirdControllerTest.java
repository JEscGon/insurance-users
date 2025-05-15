package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.service.VehicleThirdService;

import com.dev.insurance_users.generated.model.VehicleThirdDto;
import com.dev.insurance_users.infrastructure.rest.mapper.VehicleThirdDtoMapper;
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
class VehicleThirdControllerTest {

    @Mock
    private VehicleThirdService vehicleThirdService;
    @Mock
    private VehicleThirdDtoMapper vehicleThirdDtoMapper;
    @InjectMocks
    private VehicleThirdController vehicleThirdController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleThirdController).build();
        objectMapper = new ObjectMapper();
    }
    private VehicleThird createVehicleThird() {
        VehicleThird vehicle = new VehicleThird();
        vehicle.setId(1L);
        vehicle.setMatricula("1234ABC");
        return vehicle;
    }
    private VehicleThirdDto createVehicleThirdDto() {
        VehicleThirdDto dto = new VehicleThirdDto();
        dto.setId(1);
        dto.setMatricula("1234ABC");
        return dto;
    }

    @Test
    void getAllThirdVehicles_ShouldReturnVehiclesList() throws Exception {
        // Given
        VehicleThird vehicle1 = createVehicleThird();

        VehicleThird vehicle2 = new VehicleThird();
        vehicle2.setId(2L);
        vehicle2.setMatricula("5678XYZ");

        List<VehicleThird> vehicles = Arrays.asList(vehicle1, vehicle2);

        VehicleThirdDto dto1 = createVehicleThirdDto();

        VehicleThirdDto dto2 = new VehicleThirdDto();
        dto2.setId(2);
        dto2.setMatricula("5678XYZ");

        when(vehicleThirdService.findAll()).thenReturn(vehicles);
        when(vehicleThirdDtoMapper.fromDomainToDto(vehicle1)).thenReturn(dto1);
        when(vehicleThirdDtoMapper.fromDomainToDto(vehicle2)).thenReturn(dto2);

        // When & Then
        mockMvc.perform(get("/third_vehicles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].matricula", is("1234ABC")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].matricula", is("5678XYZ")));

        verify(vehicleThirdService).findAll();
    }

    @Test
    void getThirdVehicleById_WhenVehicleExists_ShouldReturnVehicle() throws Exception {
        // Given
        Long vehicleId = 1L;
        VehicleThird vehicle = createVehicleThird();
        VehicleThirdDto dto = createVehicleThirdDto();

        when(vehicleThirdService.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(vehicleThirdDtoMapper.fromDomainToDto(vehicle)).thenReturn(dto);

        // When & Then
        mockMvc.perform(get("/third_vehicles/{id}", vehicleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.matricula", is("1234ABC")));

        verify(vehicleThirdService).findById(vehicleId);
    }

    @Test
    void getThirdVehicleById_WhenVehicleDoesNotExist_ShouldReturnNotFound() throws Exception {
        // Given
        Long vehicleId = 99L;
        when(vehicleThirdService.findById(vehicleId)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/third_vehicles/{id}", vehicleId))
                .andExpect(status().isNotFound());

        verify(vehicleThirdService).findById(vehicleId);
    }

    @Test
    void getThirdVehicleById_WhenExceptionThrown_ShouldReturnBadRequest() throws Exception {
        // Given
        Long vehicleId = 1L;
        when(vehicleThirdService.findById(vehicleId)).thenThrow(new RuntimeException("Error"));

        // When & Then
        mockMvc.perform(get("/third_vehicles/{id}", vehicleId))
                .andExpect(status().isBadRequest());

        verify(vehicleThirdService).findById(vehicleId);
    }

    @Test
    void saveThirdVehicle_ShouldReturnCreated() throws Exception {
        // Given
        VehicleThirdDto dto = createVehicleThirdDto();
        VehicleThird vehicle = createVehicleThird();

        when(vehicleThirdDtoMapper.fromDtoToDomain(any(VehicleThirdDto.class))).thenReturn(vehicle);
        doNothing().when(vehicleThirdService).save(vehicle);

        // When & Then
        mockMvc.perform(post("/third_vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(vehicleThirdDtoMapper).fromDtoToDomain(any(VehicleThirdDto.class));
        verify(vehicleThirdService).save(vehicle);
    }

    @Test
    void updateThirdVehicle_ShouldReturnOk() throws Exception {
        // Given
        Long vehicleId = 1L;
        VehicleThirdDto dto = createVehicleThirdDto();
        VehicleThird vehicle = createVehicleThird();

        when(vehicleThirdDtoMapper.fromDtoToDomain(any(VehicleThirdDto.class))).thenReturn(vehicle);
        doNothing().when(vehicleThirdService).save(vehicle);

        // When & Then
        mockMvc.perform(put("/third_vehicles/{id}", vehicleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(vehicleThirdDtoMapper).fromDtoToDomain(any(VehicleThirdDto.class));
        verify(vehicleThirdService).save(vehicle);
    }

    @Test
    void updateThirdVehicle_WhenExceptionThrown_ShouldReturnBadRequest() throws Exception {
        // Given
        Long vehicleId = 1L;
        VehicleThirdDto dto = createVehicleThirdDto();

        when(vehicleThirdDtoMapper.fromDtoToDomain(any(VehicleThirdDto.class))).thenThrow(new RuntimeException("Error"));

        // When & Then
        mockMvc.perform(put("/third_vehicles/{id}", vehicleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(vehicleThirdDtoMapper).fromDtoToDomain(any(VehicleThirdDto.class));
    }

    @Test
    void deleteThirdVehicleById_ShouldReturnNoContent() throws Exception {
        // Given
        Long vehicleId = 1L;
        doNothing().when(vehicleThirdService).deleteVehicleById(vehicleId);

        // When & Then
        mockMvc.perform(delete("/third_vehicles/{id}", vehicleId))
                .andExpect(status().isNoContent());

        verify(vehicleThirdService).deleteVehicleById(vehicleId);
    }
}