package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save (User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Manejar la excepción de violación de integridad de datos
            throw new RuntimeException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByDni(String dni) {
        return userRepository.findByDni(dni);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}