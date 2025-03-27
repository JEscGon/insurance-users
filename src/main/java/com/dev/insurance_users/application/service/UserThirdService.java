package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.repository.UserThirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserThirdService {

    private final UserThirdRepository userThirdRepository;

    public void save(UserThird user) {
        userThirdRepository.save(user);
    }

    public Optional<UserThird> findById(Long id) {
        return userThirdRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userThirdRepository.deleteById(id);
    }

    public List<UserThird> findAll() {
        return userThirdRepository.findAll();
    }

}
