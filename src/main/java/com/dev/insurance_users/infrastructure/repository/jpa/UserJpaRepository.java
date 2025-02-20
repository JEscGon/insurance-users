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

    Optional<UserEntity> findUserByDni(String dni);

    List<UserEntity> findAll();

    void deleteById(Long id);

    @Modifying
    @Query("UPDATE UserEntity u SET u.email = COALESCE(:email, u.email), u.phone = COALESCE(:phone, u.phone)," +
            " u.address = COALESCE(:address, u.address), u.city = COALESCE(:city, u.city)," +
            " u.country = COALESCE(:country, u.country) WHERE u.id = :id")
    void updateById(@Param("id") Long id, @Param("email") String email, @Param("phone") String phone,
                    @Param("address") String address, @Param("city") String city, @Param("country") String country);



}
