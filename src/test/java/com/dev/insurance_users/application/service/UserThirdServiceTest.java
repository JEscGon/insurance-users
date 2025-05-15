//package com.dev.insurance_users.application.service;
//
//import com.dev.insurance_users.application.domain.UserThird;
//import com.dev.insurance_users.application.repository.UserRepository;
//import com.dev.insurance_users.application.repository.UserThirdRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserThirdServiceTest {
//
//    @Mock
//    private UserThirdRepository userThirdRepository;
//
//    @InjectMocks
//    private UserThirdService userThirdService;
//
//    @Test
//    public void testSave() {
//        // Arrange
//        UserThird user = new UserThird();
//
//        // Act
//        userThirdService.save(user);
//
//        // Assert
//        verify(userThirdRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testFindById() {
//        // Arrange
//        Long id = 1L;
//        UserThird expectedUser = new UserThird();
//        when(userThirdRepository.findById(id)).thenReturn(Optional.of(expectedUser));
//
//        // Act
//        Optional<UserThird> result = userThirdService.findById(id);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(expectedUser, result.get());
//        verify(userThirdRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testDeleteUserById() {
//        // Arrange
//        Long id = 1L;
//
//        // Act
//        userThirdService.deleteUserById(id);
//
//        // Assert
//        verify(userThirdRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    public void testFindAll() {
//        // Arrange
//        List<UserThird> expectedUsers = Arrays.asList(new UserThird(), new UserThird());
//        when(userThirdRepository.findAll()).thenReturn(expectedUsers);
//
//        // Act
//        List<UserThird> result = userThirdService.findAll();
//
//        // Assert
//        assertEquals(expectedUsers.size(), result.size());
//        assertEquals(expectedUsers, result);
//        verify(userThirdRepository, times(1)).findAll();
//    }
//
//@Test
//    public void testFindById_UserDoesNotExist() {
//        // Arrange
//        Long id = 99L;
//        when(userThirdRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act
//        Optional<UserThird> result = userThirdService.findById(id);
//
//        // Assert
//        assertTrue(result.isEmpty());
//        verify(userThirdRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testFindByDni_UserExists() {
//        // Arrange
//        String dni = "12345678X";
//        UserThird expectedUser = new UserThird();
//        when(userThirdRepository.findByDni(dni)).thenReturn(Optional.of(expectedUser));
//
//        // Act
//        Optional<UserThird> result = userThirdService.findByDni(dni);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(expectedUser, result.get());
//        verify(userThirdRepository, times(1)).findByDni(dni);
//    }
//
//    @Test
//    public void testFindByDni_UserDoesNotExist() {
//        // Arrange
//        String dni = "99999999Z";
//        when(userThirdRepository.findByDni(dni)).thenReturn(Optional.empty());
//
//        // Act
//        Optional<UserThird> result = userThirdService.findByDni(dni);
//
//        // Assert
//        assertTrue(result.isEmpty());
//        verify(userThirdRepository, times(1)).findByDni(dni);
//    }
//
//    @Test
//    public void testFindByEmail_UserExists() {
//        // Arrange
//        String email = "usuario@test.com";
//        UserThird expectedUser = new UserThird();
//        when(userThirdRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));
//
//        // Act
//        Optional<UserThird> result = userThirdService.findByEmail(email);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(expectedUser, result.get());
//        verify(userThirdRepository, times(1)).findByEmail(email);
//    }
//
//    @Test
//    public void testFindByEmail_UserDoesNotExist() {
//        // Arrange
//        String email = "noexiste@test.com";
//        when(userThirdRepository.findByEmail(email)).thenReturn(Optional.empty());
//
//        // Act
//        Optional<UserThird> result = userThirdService.findByEmail(email);
//
//        // Assert
//        assertTrue(result.isEmpty());
//        verify(userThirdRepository, times(1)).findByEmail(email);
//    }
//
//    @Test
//    public void testSave_IncrementalIdUser() {
//        // Arrange
//        UserThird userWithoutId = new UserThird();
//        userWithoutId.setId(null); // Forzar ID a null para simular un nuevo usuario
//
//        doAnswer(invocation -> {
//            UserThird userArg = invocation.getArgument(0);
//            // Simulamos que el repositorio asigna un ID
//            userArg.setId(1L);
//            return null;
//        }).when(userThirdRepository).save(userWithoutId);
//
//        // Act
//        userThirdService.save(userWithoutId);
//
//        // Assert
//        assertEquals(1L, userWithoutId.getId());
//        verify(userThirdRepository).save(userWithoutId);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        // Arrange
//        UserThird updatedUserData = new UserThird();
//        updatedUserData.setId(1L);
//        updatedUserData.setName("Nombre Nuevo");
//
//        // Act
//        userThirdService.save(updatedUserData);
//
//        // Assert
//        verify(userThirdRepository, times(1)).save(updatedUserData);
//    }
//
//
//
//}
