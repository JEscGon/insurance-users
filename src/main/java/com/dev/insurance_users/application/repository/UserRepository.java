package com.dev.insurance_users.application.repository;

import com.dev.insurance_users.application.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public void save(User user);
    public Optional<User> findById(Long id);
    public Optional<User> findByDni(String dni);
    public List<User> findAll();
    public void deleteById(Long id);
    public Optional<User> findByEmail(String email);
}
