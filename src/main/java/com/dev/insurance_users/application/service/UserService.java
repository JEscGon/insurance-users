package com.dev.insurance_users.application.service;

import com.dev.insurance_users.infrastructure.repository.jpa.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public UserEntity createUser(UserEntity user) {
        user.setDateOfRegistration(LocalDate.now());
        return userJpaRepository.save(user);
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userJpaRepository.findUserById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userJpaRepository.findAll();
    }

    @Transactional
    public Optional<UserEntity> updateUserById(Long id, UserEntity user) {
        UserEntity existingUser = userJpaRepository.findUserById(id).orElseThrow(() -> new RuntimeException("User not found"));String email = (user.getEmail() == null || user.getEmail().isEmpty()) ? existingUser.getEmail() : user.getEmail();
        String phone = (user.getPhone() == null || user.getPhone().isEmpty()) ? existingUser.getPhone() : user.getPhone();
        String address = (user.getAddress() == null || user.getAddress().isEmpty()) ? existingUser.getAddress() : user.getAddress();
        String city = (user.getCity() == null || user.getCity().isEmpty()) ? existingUser.getCity() : user.getCity();
        String country = (user.getCountry() == null || user.getCountry().isEmpty()) ? existingUser.getCountry() : user.getCountry();
        userJpaRepository.updateById(id, email, phone, address, city, country);
        return userJpaRepository.findUserById(id);
    }

    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }

    public Optional<UserEntity> getUserByDni(String dni) {
        return userJpaRepository.findUserByDni(dni);
    }


}