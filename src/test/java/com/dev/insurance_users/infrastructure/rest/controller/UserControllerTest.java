package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.application.service.UserService;
import com.dev.insurance_users.generated.model.UserDto;
import com.dev.insurance_users.infrastructure.rest.mapper.UserDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private UserDtoMapper userDtoMapper;
    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }
    private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Ana");
        user.setSurname("Pérez");
        user.setEmail("ana.perez@example.com");
        user.setDni("12345678A");
        user.setPhone("600123456");
        user.setPassword("1234");
        user.setCity("Madrid");
        user.setCountry("España");
        user.setAddress("Calle Falsa 123");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
        return user;
    }
    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("Ana");
        userDto.setSurname("Pérez");
        userDto.setEmail("ana.perez@example.com");
        userDto.setDni("12345678A");
        userDto.setPhone("600123456");
        userDto.setPassword("1234");
        userDto.setCity("Madrid");
        userDto.setCountry("España");
        userDto.setAddress("Calle Falsa 123");
        userDto.setDateOfBirth(LocalDate.of(1990, 1, 1));
        return userDto;
    }

    @Test
    void getUserById_deberiaRetornarUsuario() throws Exception {
        // GIVEN
        User user = createUser();
        UserDto userDto = createUserDto();

        when(userService.findById(1L)).thenReturn(user);
        when(userDtoMapper.fromDomainToDto(user)).thenReturn(userDto);

        // WHEN/THEN
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ana.perez@example.com"))
                .andExpect(jsonPath("$.id").value(1));

        verify(userService).findById(1L);
        verify(userDtoMapper).fromDomainToDto(user);
    }
    @Test
    void getUserById_deberiaDevolverNotFound_cuandoUsuarioNoExiste() throws Exception {
        // GIVEN
        when(userService.findById(99L)).thenThrow(ResourceNotFoundException.class);

        // WHEN/THEN
        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound());

        verify(userService).findById(99L);
    }
    @Test
    void getUserById_deberiaDevolverBadRequest_cuandoIdNoValido() throws Exception {
        // WHEN/THEN
        mockMvc.perform(get("/users/invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveUser_deberiaCrearUsuario() throws Exception {
        // GIVEN
        User user = createUser();
        UserDto userDto = createUserDto();

        when(userDtoMapper.fromDtoToDomain(any(UserDto.class))).thenReturn(user);
        doNothing().when(userService).save(user);

        // WHEN/THEN
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

        verify(userDtoMapper).fromDtoToDomain(any(UserDto.class));
        verify(userService).save(user);
    }

    @Test
    void findAllUsers_deberiaRetornarListaUsuarios() throws Exception {
        // GIVEN
        User user = createUser();
        UserDto userDto = createUserDto();

        when(userService.getAllUsers()).thenReturn(java.util.List.of(user));
        when(userDtoMapper.fromDomainToDto(user)).thenReturn(userDto);

        // WHEN/THEN
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("ana.perez@example.com"))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(userService).getAllUsers();
        verify(userDtoMapper).fromDomainToDto(user);
    }

    @Test
    void getUserByDni_deberiaRetornarUsuario() throws Exception {
        // GIVEN
        String dni = "12345678A";
        User user = createUser();
        UserDto userDto = createUserDto();

        when(userService.getUserByDni(dni)).thenReturn(user);
        when(userDtoMapper.fromDomainToDto(user)).thenReturn(userDto);

        // WHEN/THEN
        mockMvc.perform(get("/users/dni/{dni}", dni))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value(dni));

        verify(userService).getUserByDni(dni);
        verify(userDtoMapper).fromDomainToDto(user);
    }

    @Test
    void getUserByEmail_deberiaRetornarUsuario() throws Exception {
        // GIVEN
        String email = "ana.perez@example.com";
        User user = createUser();
        UserDto userDto = createUserDto();

        when(userService.getUserByEmail(email)).thenReturn(user);
        when(userDtoMapper.fromDomainToDto(user)).thenReturn(userDto);

        // WHEN/THEN
        mockMvc.perform(get("/users/email/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email));

        verify(userService).getUserByEmail(email);
        verify(userDtoMapper).fromDomainToDto(user);
    }

    @Test
    void deleteUserById_deberiaEliminarUsuario() throws Exception {
        // GIVEN
        Long userId = 1L;
        doNothing().when(userService).deleteUserById(userId);

        // WHEN/THEN
        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());

        verify(userService).deleteUserById(userId);
    }

    @Test
    void updateUser_deberiaActualizarUsuario() throws Exception {
        // GIVEN
        Long userId = 1L;
        User user = createUser();
        UserDto userDto = createUserDto();

        when(userDtoMapper.fromDtoToDomain(any(UserDto.class))).thenReturn(user);
        doNothing().when(userService).save(user);

        // WHEN/THEN
        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userDtoMapper).fromDtoToDomain(any(UserDto.class));
        verify(userService).save(user);
    }

    @Test
    void getUserByDni_deberiaDevolverNotFound_cuandoUsuarioNoExiste() throws Exception {
        // GIVEN
        String dni = "99999999X";
        when(userService.getUserByDni(dni)).thenThrow(ResourceNotFoundException.class);

        // WHEN/THEN
        mockMvc.perform(get("/users/dni/{dni}", dni))
                .andExpect(status().isNotFound());

        verify(userService).getUserByDni(dni);
    }

    @Test
    void getUserByEmail_deberiaDevolverNotFound_cuandoUsuarioNoExiste() throws Exception {
        // GIVEN
        String email = "noexiste@example.com";
        when(userService.getUserByEmail(email)).thenThrow(ResourceNotFoundException.class);

        // WHEN/THEN
        mockMvc.perform(get("/users/email/{email}", email))
                .andExpect(status().isNotFound());

        verify(userService).getUserByEmail(email);
    }

    @Test
    void deleteUserById_deberiaDevolverBadRequest_cuandoIdNoValido() throws Exception {
        // WHEN/THEN
        mockMvc.perform(delete("/users/invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUser_deberiaDevolverBadRequest_cuandoIdNoValido() throws Exception {
        // GIVEN
        UserDto userDto = createUserDto();

        // WHEN/THEN
        mockMvc.perform(put("/users/invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }

}