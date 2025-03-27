package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.repository.UserThirdRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserThirdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserThirdRepositoryImpl implements UserThirdRepository {

    private final UserThirdJpaRepository userThirdJpaRepository;
    private final UserThirdMapper userThirdMapper;

    @Override
    public void save(UserThird user) {
        if(user.getId() == null) {
            user.setDateOfRegistration(LocalDate.now());
            userThirdJpaRepository.save(userThirdMapper.fromDomainToEntity(user));
        } else {
            var existingUser = userThirdJpaRepository.findById(user.getId());
            if(existingUser.isPresent()) {
                var auxDate = existingUser.get().getDateOfRegistration();
                userThirdMapper.updateUserThirdFromExisting(userThirdMapper.fromDomainToEntity(user), existingUser.get());
                user.setDateOfLastUpdate(LocalDate.now());
                user.setDateOfRegistration(auxDate);
            }
            userThirdJpaRepository.save(userThirdMapper.fromDomainToEntity(user));
        }
    }

    @Override
    public Optional<UserThird> findById(Long id) {
        return userThirdJpaRepository.findById(id)
                .map(userThirdMapper::fromEntityToDomain);
    }

    @Override
    public List<UserThird> findAll() {
        return userThirdJpaRepository.findAll().stream()
                .map(userThirdMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userThirdJpaRepository.deleteById(id);
    }
}
