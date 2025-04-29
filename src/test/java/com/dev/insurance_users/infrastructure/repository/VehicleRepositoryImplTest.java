package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleRepositoryImplTest {

    @Mock
    private VehicleJpaRepository vehicleJpaRepository;
    @Mock
    private UserJpaRepository userJpaRepository;
    @Mock
    private VehicleMapper vehicleMapper;
    @InjectMocks
    private VehicleRepositoryImpl vehicleRepositoryImpl;


    @Test
    void save_NewVehicle_ShouldSaveVehicleWithUser() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setUserId(1);
        VehicleEntity vehicleEntity = new VehicleEntity();
        UserEntity userEntity = new UserEntity();

        when(vehicleMapper.fromDomainToEntity(vehicle)).thenReturn(vehicleEntity);
        when(userJpaRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        // Act
        vehicleRepositoryImpl.save(vehicle);

        // Assert
        verify(vehicleMapper).fromDomainToEntity(vehicle);
        verify(userJpaRepository).findById(1L);
        // Don't verify on vehicleEntity as it's not a mock
        verify(vehicleJpaRepository).save(vehicleEntity);
    }

    @Test
    void save_NewVehicle_UserNotFound_ShouldThrowException() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setUserId(1);
        VehicleEntity vehicleEntity = new VehicleEntity();

        when(vehicleMapper.fromDomainToEntity(vehicle)).thenReturn(vehicleEntity);
        when(userJpaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> vehicleRepositoryImpl.save(vehicle));
        verify(vehicleJpaRepository, never()).save(any());
    }

    @Test
    void save_ExistingVehicle_ShouldUpdateVehicle() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);  // Set ID to make it an existing vehicle
        vehicle.setUserId(2);
        VehicleEntity vehicleEntity = new VehicleEntity();
        VehicleEntity existingVehicle = new VehicleEntity();
        UserEntity userEntity = new UserEntity();
        existingVehicle.setUser(userEntity);

        when(vehicleMapper.fromDomainToEntity(vehicle)).thenReturn(vehicleEntity);
        when(vehicleJpaRepository.findById(1L)).thenReturn(Optional.of(existingVehicle));

        // Act
        vehicleRepositoryImpl.save(vehicle);

        // Assert
        verify(vehicleMapper).updateVehicleFromExisting(existingVehicle, vehicleEntity);
        verify(vehicleJpaRepository).save(existingVehicle);
    }

    @Test
    void save_ExistingVehicle_NotFound_ShouldThrowException() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);  // Set ID to make it an existing vehicle
        vehicle.setUserId(1);
        VehicleEntity vehicleEntity = new VehicleEntity();

        when(vehicleMapper.fromDomainToEntity(vehicle)).thenReturn(vehicleEntity);
        when(vehicleJpaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> vehicleRepositoryImpl.save(vehicle));
    }

    @Test
    void findById_ExistingVehicle_ShouldReturnVehicle() {
        // Arrange
        VehicleEntity vehicleEntity = new VehicleEntity();
        Vehicle vehicle = new Vehicle();
        when(vehicleJpaRepository.findById(1L)).thenReturn(Optional.of(vehicleEntity));
        when(vehicleMapper.fromEntityToDomain(vehicleEntity)).thenReturn(vehicle);

        // Act
        Optional<Vehicle> result = vehicleRepositoryImpl.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(vehicle, result.get());
    }

    @Test
    void findById_NonExistingVehicle_ShouldReturnEmpty() {
        // Arrange
        when(vehicleJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Optional<Vehicle> result = vehicleRepositoryImpl.findById(1L);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_ShouldReturnAllVehicles() {
        // Arrange
        VehicleEntity vehicleEntity1 = new VehicleEntity();
        VehicleEntity vehicleEntity2 = new VehicleEntity();
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();

        when(vehicleJpaRepository.findAll()).thenReturn(List.of(vehicleEntity1, vehicleEntity2));
        when(vehicleMapper.fromEntityToDomain(vehicleEntity1)).thenReturn(vehicle1);
        when(vehicleMapper.fromEntityToDomain(vehicleEntity2)).thenReturn(vehicle2);

        // Act
        List<Vehicle> result = vehicleRepositoryImpl.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(vehicle1));
        assertTrue(result.contains(vehicle2));
    }

    @Test
    void deleteById_ShouldDeleteVehicle() {
        // Act
        vehicleRepositoryImpl.deleteById(1L);

        // Assert
        verify(vehicleJpaRepository).deleteById(1L);
    }

    @Test
    void findByMatricula_ExistingVehicle_ShouldReturnVehicle() {
        // Arrange
        String matricula = "ABC123";
        VehicleEntity vehicleEntity = new VehicleEntity();
        Vehicle vehicle = new Vehicle();

        when(vehicleJpaRepository.findByMatricula(matricula)).thenReturn(Optional.of(vehicleEntity));
        when(vehicleMapper.fromEntityToDomain(vehicleEntity)).thenReturn(vehicle);

        // Act
        Optional<Vehicle> result = vehicleRepositoryImpl.findByMatricula(matricula);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(vehicle, result.get());
    }

    @Test
    void deleteByUserId_ShouldDeleteAllUserVehicles() {
        // Act
        vehicleRepositoryImpl.deleteByUserId(1L);
        // Assert
        verify(vehicleJpaRepository).deleteByUserId(1L);
    }
}




