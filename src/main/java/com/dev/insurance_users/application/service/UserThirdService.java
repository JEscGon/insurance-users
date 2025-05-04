package com.dev.insurance_users.application.service;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.exception.DuplicateResourceException;
import com.dev.insurance_users.application.exception.PartSaveErrorException;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.application.repository.UserThirdRepository;
import com.dev.insurance_users.infrastructure.exception.PartSaveErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserThirdService {

    private final UserThirdRepository userThirdRepository;

    public void save(UserThird userThird) {
        try {
            userThirdRepository.save(userThird);
        } catch (DuplicateResourceException e) {
            throw new PartSaveErrorException("Usuario tercero ya existe: " + userThird.getDni() + e.getMessage(), PartSaveErrorType.EXISTING_USER);
        } catch (Exception e) {
            throw new PartSaveErrorException("Error saving user: " + userThird.getId() + e.getMessage(), PartSaveErrorType.INTERNAL_SERVER_ERROR);
        }
    }

    public UserThird findById(Long id) {
        return userThirdRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userThirdRepository.deleteById(id);
    }

    public List<UserThird> findAll() {
        return userThirdRepository.findAll();
    }

    public UserThird findByDni(String dni) {
        return userThirdRepository.findByDni(dni);
    }

    public UserThird findByEmail(String email) {
        return userThirdRepository.findByEmail(email);
    }


}
