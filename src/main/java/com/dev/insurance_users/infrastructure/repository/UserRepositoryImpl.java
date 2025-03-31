package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.repository.UserRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        if (user.getId() == null) { // nuevo usuario
            user.setDateOfRegistration(LocalDate.now());
        } else { // actualización
            userJpaRepository.findById(user.getId())
                .ifPresent(existingUser -> {
                    user.setDateOfRegistration(existingUser.getDateOfRegistration());
                    user.setDateOfLastUpdate(LocalDate.now());
                });
        }
        userJpaRepository.save(userMapper.fromDomainToEntity(user));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(userMapper::fromEntityToDomain);
    }

    @Override
    public Optional<User> findByDni(String dni) {
       return userJpaRepository.findByDni(dni)
            .map(userMapper::fromEntityToDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userMapper::fromEntityToDomain);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

}
