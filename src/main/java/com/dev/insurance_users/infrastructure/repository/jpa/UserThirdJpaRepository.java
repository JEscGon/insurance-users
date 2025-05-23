package com.dev.insurance_users.infrastructure.repository.jpa;

import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserThirdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserThirdJpaRepository extends JpaRepository<UserThirdEntity, Long> {

    Optional<UserThirdEntity> findById(Long id);

    List<UserThirdEntity> findAll();

    void deleteById(Long id);
}
