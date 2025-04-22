package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.service.UserThirdService;
import com.dev.insurance_users.generated.api.ThirdUsersApi;
import com.dev.insurance_users.infrastructure.rest.mapper.UserThirdDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import com.dev.insurance_users.generated.model.UserThirdDto;

@RestController
@RequiredArgsConstructor
public class UserThirdController implements ThirdUsersApi {

    private final UserThirdService userThirdService;
    private final UserThirdDtoMapper userThirdDtoMapper;

    @Override
    public ResponseEntity<Void> deleteThirdUserById(Long id){
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        userThirdService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<UserThirdDto>> findAllThirdUsers() {
        List<UserThird> vehicles = userThirdService.findAll();
        List<UserThirdDto> userThirdDtos = vehicles.stream()
                .map(userThirdDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userThirdDtos , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserThirdDto> findThirdUserById(Long id) {
        Optional<UserThird> userThirdOpt = userThirdService.findById(id);
        if (userThirdOpt.isPresent()) {
            UserThirdDto userThirdDto = userThirdDtoMapper.fromDomainToDto(userThirdOpt.get());
            return new ResponseEntity<>(userThirdDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> saveThirdUser(UserThirdDto userThirdDto){
        UserThird userThird = userThirdDtoMapper.fromDtoToDomain(userThirdDto);
        userThirdService.save(userThird);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateThirdUser(Long id, UserThirdDto userThirdDto){
        try {
            userThirdDto.setId(Math.toIntExact(id));
            UserThird userThird = userThirdDtoMapper.fromDtoToDomain(userThirdDto);
            userThirdService.save(userThird);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
