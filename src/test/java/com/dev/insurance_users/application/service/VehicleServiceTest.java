package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void save_shouldDelegateToRepository() {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setMatricula("ABC123");

        // When
        vehicleService.save(vehicle);

        // Then
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    void findById_shouldReturnVehicleWhenExists() {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        // When
        Optional<Vehicle> result = vehicleService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(vehicleRepository).findById(1L);
    }

    @Test
    void findById_shouldReturnEmptyWhenNotExists() {
        // Given
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Vehicle> result = vehicleService.findById(1L);

        // Then
        assertTrue(result.isEmpty());
        verify(vehicleRepository).findById(1L);
    }

    @Test
    void findByMatricula_shouldReturnVehicleWhenExists() {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setMatricula("ABC123");
        when(vehicleRepository.findByMatricula("ABC123")).thenReturn(Optional.of(vehicle));

        // When
        Optional<Vehicle> result = vehicleService.findByMatricula("ABC123");

        // Then
        assertTrue(result.isPresent());
        assertEquals("ABC123", result.get().getMatricula());
        verify(vehicleRepository).findByMatricula("ABC123");
    }

    @Test
    void findAll_shouldReturnAllVehicles() {
        // Given
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1L);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2L);
        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // When
        List<Vehicle> result = vehicleService.findAll();

        // Then
        assertEquals(2, result.size());
        verify(vehicleRepository).findAll();
    }

    @Test
    void deleteVehicleById_shouldDelegateToRepository() {
        // Given
        Long id = 1L;

        // When
        vehicleService.deleteVehicleById(id);

        // Then
        verify(vehicleRepository).deleteById(id);
    }
}
