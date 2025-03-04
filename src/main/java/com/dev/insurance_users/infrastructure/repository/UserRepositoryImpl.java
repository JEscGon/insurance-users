package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.repository.UserRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor

public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;

    public User save(Long id, User user) {
        UserEntity userEntity = userMapper.fromDomainToEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return userMapper.fromEntityToDomain(savedEntity);
    }
    public User findById(Long id) {
        return userJpaRepository.findById(id)
                .map(userMapper::fromEntityToDomain)
                .orElse(null);
    }
    public User findByDni(String dni) {
       return userJpaRepository.findByDni(dni)
            .map(userMapper::fromEntityToDomain)
            .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userMapper::fromEntityToDomain)
                .orElse(null);
    }

    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }



}
