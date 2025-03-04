package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.repository.UserRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void save(Long id , User user) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findById(id));
        if (id == null) {
            user.setDateOfRegistration(LocalDate.now());
            userRepository.save(null, user);
        } else {

            if (existingUser.isPresent()) {
                if (user.getName() == null) user.setName(existingUser.get().getName());
                if (user.getSurname() == null) user.setSurname(existingUser.get().getSurname());
                if (user.getPhone() == null) user.setPhone(existingUser.get().getPhone());
                if (user.getEmail() == null) user.setEmail(existingUser.get().getEmail());
                if (user.getDni() == null) user.setDni(existingUser.get().getDni());
                if (user.getPassword() == null) user.setPassword(existingUser.get().getPassword());
                if (user.getCity() == null) user.setCity(existingUser.get().getCity());
                if (user.getCountry() == null) user.setCountry(existingUser.get().getCountry());
                if (user.getAddress() == null) user.setAddress(existingUser.get().getAddress());
                if (user.getDateOfBirth() == null) user.setDateOfBirth(existingUser.get().getDateOfBirth());
                if (user.getDateOfRegistration() == null) user.setDateOfRegistration(existingUser.get().getDateOfRegistration());
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

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }


}