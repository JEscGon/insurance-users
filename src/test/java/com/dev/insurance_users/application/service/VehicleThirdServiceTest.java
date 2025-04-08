package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.repository.VehicleThirdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleThirdServiceTest {

    @Mock
    private VehicleThirdRepository vehicleThirdRepository;

    @InjectMocks
    private VehicleThirdService vehicleThirdService;

    @Test
    void testSave() {
        // Arrange
        VehicleThird vehicle = new VehicleThird();

        // Act
        vehicleThirdService.save(vehicle);

        // Assert
        verify(vehicleThirdRepository).save(vehicle);
    }

    @Test
    void testFindById() {
        // Arrange
        Long id = 1L;
        VehicleThird vehicle = new VehicleThird();
        when(vehicleThirdRepository.findById(id)).thenReturn(Optional.of(vehicle));

        // Act
        Optional<VehicleThird> result = vehicleThirdService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(vehicle, result.get());
        verify(vehicleThirdRepository).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        Long id = 1L;
        when(vehicleThirdRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<VehicleThird> result = vehicleThirdService.findById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(vehicleThirdRepository).findById(id);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<VehicleThird> vehicles = List.of(new VehicleThird(), new VehicleThird());
        when(vehicleThirdRepository.findAll()).thenReturn(vehicles);

        // Act
        List<VehicleThird> result = vehicleThirdService.findAll();

        // Assert
        assertEquals(vehicles, result);
        verify(vehicleThirdRepository).findAll();
    }

    @Test
    void testDeleteVehicleById() {
        // Arrange
        Long id = 1L;

        // Act
        vehicleThirdService.deleteVehicleById(id);

        // Assert
        verify(vehicleThirdRepository).deleteById(id);
    }
}
