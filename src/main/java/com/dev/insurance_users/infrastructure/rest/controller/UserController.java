package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.service.UserService;
import com.dev.insurance_users.generated.api.UsersApi;
import com.dev.insurance_users.generated.model.UserDto;
import com.dev.insurance_users.infrastructure.rest.controller.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Override
    public ResponseEntity<Void> save(UserDto userDto) {
        User user = userDtoMapper.fromDtoToDomain(userDto);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(userDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> findById(String id) {
        try {
            Long userId = Long.parseLong(id);
            Optional<User> userOptional = userService.findById(userId);
            return userOptional
                    .map(user -> new ResponseEntity<>(userDtoMapper.fromDomainToDto(user), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<UserDto> getUserByDni(String dni) {
        Optional<User> userOptional = userService.getUserByDni(dni);
        return userOptional
                .map(user -> new ResponseEntity<>(userDtoMapper.fromDomainToDto(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<UserDto> getUserByEmail(String email) {
        Optional<User> userOptional = userService.getUserByEmail(email);
        return userOptional
                .map(user -> new ResponseEntity<>(userDtoMapper.fromDomainToDto(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String id) {
        try {
            Long userId = Long.parseLong(id);
            userService.deleteUserById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<UserDto> updateUser(String id, UserDto userDto) {
        try {
            Long userId = Long.parseLong(id);
            userDto.setId(Math.toIntExact(userId));
            User updatedUser = userDtoMapper.fromDtoToDomain(userDto);
            userService.save(updatedUser);
            return new ResponseEntity<>(userDtoMapper.fromDomainToDto(updatedUser), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}