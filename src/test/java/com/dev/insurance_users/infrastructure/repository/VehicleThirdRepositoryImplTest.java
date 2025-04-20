package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.VehicleThirdMapper;
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
public class VehicleThirdRepositoryImplTest {

    @Mock
    private VehicleThirdJpaRepository vehicleThirdJpaRepository;
    @Mock
    private UserThirdJpaRepository userThirdJpaRepository;

    @Mock
    private VehicleThirdMapper vehicleThirdMapper;

    @InjectMocks
    private VehicleThirdRepositoryImpl vehicleThirdRepositoryImpl;


    @Test
    void save_NewVehicle_Success() {
        // Given
        VehicleThird vehicleThird = new VehicleThird();
        vehicleThird.setId(null);
        vehicleThird.setUserThirdId(1L);

        VehicleThirdEntity vehicleThirdEntity = new VehicleThirdEntity();
        UserThirdEntity userThirdEntity = new UserThirdEntity();

        when(vehicleThirdMapper.fromDomainToEntity(vehicleThird)).thenReturn(vehicleThirdEntity);
        when(userThirdJpaRepository.findById(1L)).thenReturn(Optional.of(userThirdEntity));

        // When
        vehicleThirdRepositoryImpl.save(vehicleThird);

        // Then
        verify(vehicleThirdJpaRepository).save(vehicleThirdEntity);
        verify(userThirdJpaRepository).findById(1L);
        assertEquals(userThirdEntity, vehicleThirdEntity.getUserThird());
    }

    @Test
    void save_UpdateVehicle_Success() {
        // Given
        VehicleThird vehicleThird = new VehicleThird();
        vehicleThird.setId(1L);
        vehicleThird.setUserThirdId(2L);

        VehicleThirdEntity existingEntity = new VehicleThirdEntity();
        VehicleThirdEntity updatedEntity = new VehicleThirdEntity();
        UserThirdEntity userThirdEntity = new UserThirdEntity();
        existingEntity.setUserThird(userThirdEntity);

        when(vehicleThirdMapper.fromDomainToEntity(vehicleThird)).thenReturn(updatedEntity);
        when(vehicleThirdJpaRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        // When
        vehicleThirdRepositoryImpl.save(vehicleThird);

        // Then
        verify(vehicleThirdMapper).updateVehicleThirdFromExisting(existingEntity, updatedEntity);
        verify(vehicleThirdJpaRepository).save(existingEntity);
        assertEquals(userThirdEntity, existingEntity.getUserThird());
    }

    @Test
    void save_UpdateVehicle_NotFound() {
        // Given
        VehicleThird vehicleThird = new VehicleThird();
        vehicleThird.setId(1L);

        VehicleThirdEntity vehicleThirdEntity = new VehicleThirdEntity();
        when(vehicleThirdMapper.fromDomainToEntity(vehicleThird)).thenReturn(vehicleThirdEntity);
        when(vehicleThirdJpaRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> vehicleThirdRepositoryImpl.save(vehicleThird));
    }

    @Test
    void save_NewVehicle_UserNotFound() {
        // Given
        VehicleThird vehicleThird = new VehicleThird();
        vehicleThird.setId(null);
        vehicleThird.setUserThirdId(1L);

        VehicleThirdEntity vehicleThirdEntity = new VehicleThirdEntity();
        when(vehicleThirdMapper.fromDomainToEntity(vehicleThird)).thenReturn(vehicleThirdEntity);
        when(userThirdJpaRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> vehicleThirdRepositoryImpl.save(vehicleThird));
    }

    @Test
    void findById_Found() {
        // Given
        Long id = 1L;
        VehicleThirdEntity vehicleThirdEntity = new VehicleThirdEntity();
        VehicleThird vehicleThird = new VehicleThird();

        when(vehicleThirdJpaRepository.findById(id)).thenReturn(Optional.of(vehicleThirdEntity));
        when(vehicleThirdMapper.fromEntityToDomain(vehicleThirdEntity)).thenReturn(vehicleThird);

        // When
        Optional<VehicleThird> result = vehicleThirdRepositoryImpl.findById(id);

        // Then
        assertTrue(result.isPresent());
        assertEquals(vehicleThird, result.get());
    }

    @Test
    void findById_NotFound() {
        // Given
        Long id = 1L;
        when(vehicleThirdJpaRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<VehicleThird> result = vehicleThirdRepositoryImpl.findById(id);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll() {
        // Given
        List<VehicleThirdEntity> entities = List.of(new VehicleThirdEntity(), new VehicleThirdEntity());
        VehicleThird vehicle1 = new VehicleThird();
        VehicleThird vehicle2 = new VehicleThird();

        when(vehicleThirdJpaRepository.findAll()).thenReturn(entities);
        when(vehicleThirdMapper.fromEntityToDomain(entities.get(0))).thenReturn(vehicle1);
        when(vehicleThirdMapper.fromEntityToDomain(entities.get(1))).thenReturn(vehicle2);

        // When
        List<VehicleThird> result = vehicleThirdRepositoryImpl.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals(vehicle1, result.get(0));
        assertEquals(vehicle2, result.get(1));
    }

    @Test
    void deleteById() {
        // Given
        Long id = 1L;

        // When
        vehicleThirdRepositoryImpl.deleteById(id);

        // Then
        verify(vehicleThirdJpaRepository).deleteById(id);
    }

    @Test
    void findByMatricula_Found() {
        // Given
        String matricula = "ABC123";
        VehicleThirdEntity vehicleThirdEntity = new VehicleThirdEntity();
        VehicleThird vehicleThird = new VehicleThird();

        when(vehicleThirdJpaRepository.findByMatricula(matricula)).thenReturn(Optional.of(vehicleThirdEntity));
        when(vehicleThirdMapper.fromEntityToDomain(vehicleThirdEntity)).thenReturn(vehicleThird);

        // When
        Optional<VehicleThird> result = vehicleThirdRepositoryImpl.findByMatricula(matricula);

        // Then
        assertTrue(result.isPresent());
        assertEquals(vehicleThird, result.get());
    }

    @Test
    void findByMatricula_NotFound() {
        // Given
        String matricula = "ABC123";
        when(vehicleThirdJpaRepository.findByMatricula(matricula)).thenReturn(Optional.empty());

        // When
        Optional<VehicleThird> result = vehicleThirdRepositoryImpl.findByMatricula(matricula);

        // Then
        assertTrue(result.isEmpty());
    }

}
