package com.dev.insurance_users.application.repository;


import com.dev.insurance_users.application.domain.UserThird;

import java.util.List;
import java.util.Optional;

public interface UserThirdRepository {

    public void save(UserThird user);

    public Optional<UserThird> findById(Long id);

    public List<UserThird> findAll();

    public void deleteById(Long id);
    public Optional<UserThird> findByDni(String dni);
    public Optional<UserThird> findByEmail(String email);

}
