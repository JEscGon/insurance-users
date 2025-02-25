package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.repository.UserRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public void save(Long id , User user) {
        if (id == null) {
            userRepository.save(null, user);
        } else {
            Optional<User> existingUser = Optional.ofNullable(userRepository.findById(id));
            if (existingUser.isPresent()) {
                user.setDateOfLastUpdate(LocalDate.now());
                userMapper.fromDomainToEntity(user);
                userRepository.save(id, user);
            } else {
                throw new IllegalArgumentException("User with id " + id + " does not exist.");
            }
        }
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    public Optional<User> getUserByDni(String dni) {
        return Optional.ofNullable(userRepository.findByDni(dni));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



}