package com.dev.insurance_users.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findByPhone(String phone);

    List<UserEntity> findAll();

    void deleteByEmail(String email);

    void deleteById(Long id);

    // Métodos de actualización personalizados
    @Modifying
    @Query("UPDATE UserEntity u SET u.email = :email, u.phone = :phone WHERE u.id = :id")
    void updateById(@Param("id") Long id, @Param("email") String email, @Param("phone") String phone);

    @Modifying
    @Query("UPDATE UserEntity u SET u.email = :email, u.phone = :phone WHERE u.email = :oldEmail")
    void updateByEmail(@Param("oldEmail") String oldEmail, @Param("email") String email, @Param("phone") String phone);

}
