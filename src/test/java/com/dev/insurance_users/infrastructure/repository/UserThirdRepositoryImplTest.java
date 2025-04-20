package com.dev.insurance_users.infrastructure.repository;


import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserThirdMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserThirdRepositoryImplTest {

    @Mock
    private UserThirdJpaRepository userThirdJpaRepository;
    @Mock
    private UserThirdMapper userThirdMapper;
    @InjectMocks
    private UserThirdRepositoryImpl userThirdRepositoryImpl;

    @Test
    void save_WhenNewUser_ShouldSaveEntity() {
        // Arrange
        UserThird userThird = new UserThird();
        UserThirdEntity entity = new UserThirdEntity();
        when(userThirdMapper.fromDomainToEntity(userThird)).thenReturn(entity);

        // Act
        userThirdRepositoryImpl.save(userThird);

        // Assert
        verify(userThirdJpaRepository).save(entity);
    }

    @Test
    void save_WhenExistingUser_ShouldUpdateAndSave() {
        // Arrange
        UserThird userThird = new UserThird();
        userThird.setId(1L);
        UserThirdEntity entity = new UserThirdEntity();
        UserThirdEntity existingEntity = new UserThirdEntity();

        when(userThirdMapper.fromDomainToEntity(userThird)).thenReturn(entity);
        when(userThirdJpaRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        // Act
        userThirdRepositoryImpl.save(userThird);

        // Assert
        verify(userThirdMapper).updateUserThirdFromExisting(existingEntity, entity);
        verify(userThirdJpaRepository).save(existingEntity);
    }

    @Test
    void findById_WhenUserExists_ShouldReturnUser() {
        // Arrange
        Long id = 1L;
        UserThirdEntity entity = new UserThirdEntity();
        UserThird userThird = new UserThird();

        when(userThirdJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userThirdMapper.fromEntityToDomain(entity)).thenReturn(userThird);

        // Act
        Optional<UserThird> result = userThirdRepositoryImpl.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertSame(userThird, result.get());
    }

    @Test
    void findById_WhenUserDoesNotExist_ShouldReturnEmpty() {
        // Arrange
        Long id = 1L;
        when(userThirdJpaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<UserThird> result = userThirdRepositoryImpl.findById(id);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        // Arrange
        List<UserThirdEntity> entities = List.of(new UserThirdEntity(), new UserThirdEntity());
        UserThird user1 = new UserThird();
        UserThird user2 = new UserThird();

        when(userThirdJpaRepository.findAll()).thenReturn(entities);
        // Use consecutive returns instead of specific object matching
        when(userThirdMapper.fromEntityToDomain(any(UserThirdEntity.class)))
            .thenReturn(user1)
            .thenReturn(user2);

        // Act
        List<UserThird> result = userThirdRepositoryImpl.findAll();

        // Assert
        assertEquals(2, result.size());
        assertSame(user1, result.get(0));
        assertSame(user2, result.get(1));
    }

    @Test
    void deleteById_ShouldCallRepositoryDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        userThirdRepositoryImpl.deleteById(id);

        // Assert
        verify(userThirdJpaRepository).deleteById(id);
    }

    @Test
    void findByDni_ShouldReturnEmpty() {
        // Act
        Optional<UserThird> result = userThirdRepositoryImpl.findByDni("12345678X");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void findByEmail_ShouldReturnEmpty() {
        // Act
        Optional<UserThird> result = userThirdRepositoryImpl.findByEmail("test@example.com");

        // Assert
        assertFalse(result.isPresent());
    }
}