package com.dev.insurance_users.infrastructure.repository;


import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.rest.mapper.UserThirdMapper;
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
    void save_WhenUserIsNew_ShouldSaveAndReturnId() {
        // Arrange
        UserThird user = new UserThird();
        user.setName("New User");
        UserThirdEntity entity = new UserThirdEntity();
        entity.setId(1L);

        when(userThirdMapper.fromDomainToEntity(user)).thenReturn(entity);
        when(userThirdJpaRepository.save(entity)).thenReturn(entity);

        // Act
        Integer result = userThirdRepositoryImpl.save(user);

        // Assert
        assertEquals(1, result);
        verify(userThirdJpaRepository).save(entity);
    }

    @Test
    void save_WhenUserAlreadyExists_ShouldUpdateAndReturnId() {
        // Arrange
        UserThird user = new UserThird();
        user.setId(1L);
        user.setName("Updated User");
        UserThirdEntity existingEntity = new UserThirdEntity();
        UserThirdEntity updatedEntity = new UserThirdEntity();
        updatedEntity.setId(1L);

        when(userThirdJpaRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
        when(userThirdJpaRepository.save(existingEntity)).thenReturn(updatedEntity);
        when(userThirdMapper.fromDomainToEntity(user)).thenReturn(updatedEntity);

        // Act
        Integer result = userThirdRepositoryImpl.save(user);

        // Assert
        assertEquals(1, result);
        verify(userThirdJpaRepository).findById(1L);
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
        Optional<UserThird> result = Optional.ofNullable(userThirdRepositoryImpl.findById(id));

        // Assert
        assertTrue(result.isPresent());
        assertSame(userThird, result.get());
    }

    @Test
    void findById_WhenUserDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        when(userThirdJpaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userThirdRepositoryImpl.findById(id));
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
    void findByDni_ShouldThrowResourceNotFoundException() {
        // Arrange
        String dni = "12345678X";
        when(userThirdJpaRepository.findByDni(dni)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userThirdRepositoryImpl.findByDni(dni));
    }

    @Test
    void findByEmail_WhenEmailDoesNotExist_ShouldReturnEmpty() {
        // Arrange
        String email = "test@example.com";
        when(userThirdJpaRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userThirdRepositoryImpl.findByEmail(email));
    }

}