//package com.dev.insurance_users.infrastructure.rest.controller;
//
//import com.dev.insurance_users.application.domain.UserThird;
//import com.dev.insurance_users.application.service.UserThirdService;
//import com.dev.insurance_users.generated.model.UserThirdDto;
//import com.dev.insurance_users.infrastructure.rest.mapper.UserThirdDtoMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.hamcrest.CoreMatchers.is;
//
//@ExtendWith(MockitoExtension.class)
//public class UserThirdControllerTest {
//
//    @Mock
//    private UserThirdService userThirdService;
//
//    @Mock
//    private UserThirdDtoMapper userThirdDtoMapper;
//
//    @InjectMocks
//    private UserThirdController userThirdController;
//
//    private MockMvc mockMvc;
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(userThirdController).build();
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void findAllThirdUsers_ShouldReturnUsersList() throws Exception {
//        // Given
//        UserThird user1 = new UserThird();
//        user1.setId(1L);
//        user1.setName("Test User 1");
//
//        UserThird user2 = new UserThird();
//        user2.setId(2L);
//        user2.setName("Test User 2");
//
//        List<UserThird> users = Arrays.asList(user1, user2);
//
//        UserThirdDto dto1 = new UserThirdDto();
//        dto1.setId(1);
//        dto1.setName("Test User 1");
//
//        UserThirdDto dto2 = new UserThirdDto();
//        dto2.setId(2);
//        dto2.setName("Test User 2");
//
//        when(userThirdService.findAll()).thenReturn(users);
//        when(userThirdDtoMapper.fromDomainToDto(user1)).thenReturn(dto1);
//        when(userThirdDtoMapper.fromDomainToDto(user2)).thenReturn(dto2);
//
//        // When & Then
//        mockMvc.perform(get("/third_users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[1].id", is(2)));
//    }
//
//    @Test
//    void findThirdUserById_WhenUserExists_ShouldReturnUser() throws Exception {
//        // Given
//        Long userId = 1L;
//        UserThird user = new UserThird();
//        user.setId(userId);
//        user.setName("Test User");
//
//        UserThirdDto dto = new UserThirdDto();
//        dto.setId(1);
//        dto.setName("Test User");
//
//        when(userThirdService.findById(userId)).thenReturn(Optional.of(user));
//        when(userThirdDtoMapper.fromDomainToDto(user)).thenReturn(dto);
//
//        // When & Then
//        mockMvc.perform(get("/third_users/{id}", userId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Test User")));
//    }
//
//    @Test
//    void findThirdUserById_WhenUserDoesNotExist_ShouldReturnNotFound() throws Exception {
//        // Given
//        Long userId = 99L;
//        when(userThirdService.findById(userId)).thenReturn(Optional.empty());
//
//        // When & Then
//        mockMvc.perform(get("/third_users/{id}", userId))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void saveThirdUser_ShouldReturnCreated() throws Exception {
//        // Given
//        UserThirdDto dto = new UserThirdDto();
//        dto.setName("New User");
//
//        UserThird user = new UserThird();
//        user.setName("New User");
//
//        when(userThirdDtoMapper.fromDtoToDomain(any(UserThirdDto.class))).thenReturn(user);
//        doNothing().when(userThirdService).save(user);
//
//        // When & Then
//        mockMvc.perform(post("/third_users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void updateThirdUser_ShouldReturnOk() throws Exception {
//        // Given
//        Long userId = 1L;
//        UserThirdDto dto = new UserThirdDto();
//        dto.setId(1);
//        dto.setName("Updated User");
//
//        UserThird user = new UserThird();
//        user.setId(userId);
//        user.setName("Updated User");
//
//        when(userThirdDtoMapper.fromDtoToDomain(any(UserThirdDto.class))).thenReturn(user);
//        doNothing().when(userThirdService).save(user);
//
//        // When & Then
//        mockMvc.perform(put("/third_users/{id}", userId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteThirdUserById_ShouldReturnNoContent() throws Exception {
//        // Given
//        Long userId = 1L;
//        doNothing().when(userThirdService).deleteUserById(userId);
//
//        // When & Then
//        mockMvc.perform(delete("/third_users/{id}", userId))
//                .andExpect(status().isNoContent());
//
//        verify(userThirdService, times(1)).deleteUserById(userId);
//    }
//
//}
