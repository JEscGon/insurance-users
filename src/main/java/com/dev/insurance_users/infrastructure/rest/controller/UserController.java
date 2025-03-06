package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.service.UserService;
import com.dev.insurance_users.generated.model.UserDto;
import com.dev.insurance_users.infrastructure.rest.controller.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: Cambiar a dto / domain y mapear
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto user) {
        User createdUser = userDtoMapper.fromDtoToDomain(user);
        userService.save(createdUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> aux = userService.findById(id);
        return aux.map(user -> new ResponseEntity<>(userDtoMapper.fromDomainToDto(user), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> list = users.stream().map(userDtoMapper::fromDomainToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(Math.toIntExact(id));
        User updatedUser = userDtoMapper.fromDtoToDomain(user);
        userService.save(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/dni/{dni}")
    public ResponseEntity<UserDto> getUserByDni(@PathVariable String dni) {
        Optional<User> user = userService.getUserByDni(dni);
        return user.map(value -> new ResponseEntity<>(userDtoMapper.fromDomainToDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(userDtoMapper.fromDomainToDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}