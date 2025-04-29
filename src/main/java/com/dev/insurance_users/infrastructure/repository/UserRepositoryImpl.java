package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.application.exception.DuplicateResourceException;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.application.repository.UserRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        try {
            UserEntity userAux = userMapper.fromDomainToEntity(user);
            if (userAux.getId() == null) { // nuevo usuario
                if (userJpaRepository.findByDni(user.getDni()).isPresent()) {
                    throw new DuplicateResourceException("Ya existe un usuario con DNI: " + user.getDni());
                }
                userJpaRepository.save(userAux);
            } else { // actualización
                var aux = userJpaRepository.findById(user.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + user.getId()));
                userMapper.updateUserFromExisting(aux, userAux);
                userJpaRepository.save(aux);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Error de integridad de datos al guardar usuario. Detalles: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return userJpaRepository.findById(id).map(userMapper::fromEntityToDomain);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el usuario con id: " + id + ". Detalles: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByDni(String dni) {
        try {
            return userJpaRepository.findByDni(dni).map(userMapper::fromEntityToDomain);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el usuario con DNI: " + dni + ". Detalles: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return userJpaRepository.findByEmail(email).map(userMapper::fromEntityToDomain);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el usuario con email: " + email + ". Detalles: " + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        try {
            userJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No se encontró el usuario tercero con id: " + id + " para eliminar.");
        }
    }

}
