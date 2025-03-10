package com.dev.insurance_users.infrastructure.repository.jpa;

import com.dev.insurance_users.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// obj dom --> obj entity
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByDni(String dni);

    Optional<UserEntity> findByEmail(String nombre);

    List<UserEntity> findAll();

    void deleteById(Long id);

}
