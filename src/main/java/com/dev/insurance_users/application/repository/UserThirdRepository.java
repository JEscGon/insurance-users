package com.dev.insurance_users.application.repository;


import com.dev.insurance_users.application.domain.UserThird;

import java.util.List;

public interface UserThirdRepository {

    Integer save(UserThird user);

    public UserThird findById(Long id);

    public List<UserThird> findAll();

    public void deleteById(Long id);
    public UserThird findByDni(String dni);
    public UserThird findByEmail(String email);


}
