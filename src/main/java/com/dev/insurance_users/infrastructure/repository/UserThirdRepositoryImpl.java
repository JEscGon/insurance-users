package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.exception.DuplicateResourceException;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.application.repository.UserThirdRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserThirdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserThirdRepositoryImpl implements UserThirdRepository {

    private final UserThirdJpaRepository userThirdJpaRepository;
    private final UserThirdMapper userThirdMapper;

    @Override
    public void save(UserThird user) {
        try {
            UserThirdEntity userAux = userThirdMapper.fromDomainToEntity(user);
            if (user.getId() == null) {
                userThirdJpaRepository.save(userAux);
            } else {
                var aux = userThirdJpaRepository.findById(user.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario tercero no encontrado con id: " + user.getId()));
                userThirdMapper.updateUserThirdFromExisting(aux, userAux);
                userThirdJpaRepository.save(aux);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Error de integridad de datos al guardar usuario. Detalles: " + e.getMessage());
        }
    }

    @Override
    public UserThird findById(Long id) {
        return userThirdMapper.fromEntityToDomain(userThirdJpaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Error al buscar el usuario con id: " + id)));
    }

    @Override
    public List<UserThird> findAll() {
        return userThirdJpaRepository.findAll().stream()
                .map(userThirdMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        try {
            userThirdJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No se encontró el usuario tercero con id: " + id + " para eliminar.");
        }
    }

    @Override
    public UserThird findByDni(String dni) {
        return userThirdMapper.fromEntityToDomain(userThirdJpaRepository.findByDni(dni).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario tercero con DNI: " + dni)));
    }

    @Override
    public UserThird findByEmail(String email) {
        return userThirdMapper.fromEntityToDomain(userThirdJpaRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Error al buscar el usuario con email:" )));
    }
}
