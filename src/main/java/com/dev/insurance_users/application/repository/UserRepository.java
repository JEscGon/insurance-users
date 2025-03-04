package com.dev.insurance_users.application.repository;

import com.dev.insurance_users.application.domain.User;

import java.util.List;

public interface UserRepository {
    public User save(Long id ,User user);
    public User findById(Long id);
    public User findByDni(String dni);
    public List<User> findAll();
    public void deleteById(Long id);
    public User findByEmail(String email);
}
