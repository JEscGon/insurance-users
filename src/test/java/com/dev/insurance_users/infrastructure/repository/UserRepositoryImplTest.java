package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest {

    @Mock
    private UserJpaRepository userJpaRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    void findById_UserExists_ReturnsUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2L);
        User user = new User();
        user.setId(2L);

        when(userJpaRepository.findById(2L)).thenReturn(Optional.of(userEntity));
        when(userMapper.fromEntityToDomain(userEntity)).thenReturn(user);

        User result = userRepositoryImpl.findById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getId());
    }
    @Test
    void findById_UserDoesNotExist_ReturnsEmpty() {
        when(userJpaRepository.findById(99L)).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> userRepositoryImpl.findById(99L));

    }
    @Test
    void findByDni_UserExists_ReturnsUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setDni("12345678A");
        User user = new User();
        user.setDni("12345678A");

        when(userJpaRepository.findByDni("12345678A")).thenReturn(Optional.of(userEntity));
        when(userMapper.fromEntityToDomain(userEntity)).thenReturn(user);

        User result = userRepositoryImpl.findByDni("12345678A");

        assertNotNull(result);
        assertEquals("12345678A", result.getDni());
    }
    @Test
    void findByDni_UserDoesNotExist_ReturnsEmpty() {
        when(userJpaRepository.findByDni("99999999Z")).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> userRepositoryImpl.findByDni("99999999Z"));

    }
    @Test
    void findByEmail_UserExists_ReturnsUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("user@test.com");
        User user = new User();
        user.setEmail("user@test.com");

        when(userJpaRepository.findByEmail("user@test.com")).thenReturn(Optional.of(userEntity));
        when(userMapper.fromEntityToDomain(userEntity)).thenReturn(user);

        User result = userRepositoryImpl.findByEmail("user@test.com");

        assertNotNull(result);
        assertEquals("user@test.com", result.getEmail());
    }
    @Test
    void findByEmail_UserDoesNotExist_ReturnsEmpty() {
        when(userJpaRepository.findByEmail("nonexistent@test.com")).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> userRepositoryImpl.findByEmail("nonexistent@test.com"));
    }
    @Test
    void findAll_ReturnsListOfUsers() {
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        List<UserEntity> userEntities = Arrays.asList(user1, user2);

        User domainUser1 = new User();
        domainUser1.setId(1L);
        User domainUser2 = new User();
        domainUser2.setId(2L);

        when(userJpaRepository.findAll()).thenReturn(userEntities);
        when(userMapper.fromEntityToDomain(user1)).thenReturn(domainUser1);
        when(userMapper.fromEntityToDomain(user2)).thenReturn(domainUser2);

        List<User> result = userRepositoryImpl.findAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }
    @Test
    void findAll_EmptyList_ReturnsEmptyList() {
        when(userJpaRepository.findAll()).thenReturn(List.of());
        List<User> result = userRepositoryImpl.findAll();
        assertTrue(result.isEmpty());
    }
    @Test
    void save_newUser_savesUser() {
        User domainUser = new User();
        domainUser.setName("John");

        UserEntity userEntity = new UserEntity();
        userEntity.setName("John");
        userEntity.setId(null); // Es un usuario nuevo

        when(userMapper.fromDomainToEntity(domainUser)).thenReturn(userEntity);

        userRepositoryImpl.save(domainUser);

        verify(userJpaRepository).save(userEntity);
    }
    @Test
    void save_existingUser_updatesAndSavesUser() {
        User domainUser = new User();
        domainUser.setId(1L);
        domainUser.setName("John Updated");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John Updated");

        UserEntity existingEntity = new UserEntity();
        existingEntity.setId(1L);
        existingEntity.setName("John");

        when(userMapper.fromDomainToEntity(domainUser)).thenReturn(userEntity);
        when(userJpaRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        userRepositoryImpl.save(domainUser);

        verify(userMapper).updateUserFromExisting(existingEntity, userEntity);
        verify(userJpaRepository).save(existingEntity);
    }
    @Test
    void deleteById_callsRepositoryDeleteById() {
        Long userId = 1L;

        userRepositoryImpl.deleteById(userId);

        verify(userJpaRepository).deleteById(userId);
    }
}
