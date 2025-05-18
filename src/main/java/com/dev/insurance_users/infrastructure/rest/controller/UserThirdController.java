package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.service.UserThirdService;
import com.dev.insurance_users.generated.api.ThirdUsersApi;
import com.dev.insurance_users.generated.model.ThirdPartyUserWrapperDto;
import com.dev.insurance_users.generated.model.UserThirdDto;
import com.dev.insurance_users.infrastructure.rest.mapper.UserThirdDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<ThirdPartyUserWrapperDto> findAllThirdUsers() {
        List<UserThird> vehicles = userThirdService.findAll();
        List<UserThirdDto> userThirdDtos = vehicles.stream()
                .map(userThirdDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());

        var userThirdWrapperDto = new ThirdPartyUserWrapperDto();
        userThirdWrapperDto.setUsers(userThirdDtos);
        return new ResponseEntity<>(userThirdWrapperDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserThirdDto> findThirdUserById(Long id) {
        try {
            UserThird userThirdOpt = userThirdService.findById(id);
            UserThirdDto userThirdDto = userThirdDtoMapper.fromDomainToDto(userThirdOpt);
            return new ResponseEntity<>(userThirdDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<Integer>> saveThirdUser(ThirdPartyUserWrapperDto usersThirdDto) {
        if (usersThirdDto == null || usersThirdDto.getUsers() == null || usersThirdDto.getUsers().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<UserThird> usersToSave = usersThirdDto.getUsers().stream()
                .map(userThirdDtoMapper::fromDtoToDomain)
                .collect(Collectors.toList());

        List<Integer> savedUsers = usersToSave.stream()
                .map(userThirdService::save)
                .collect(Collectors.toList());

        if (savedUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<Integer> savedUserIds = savedUsers.stream()
            .map(Math::toIntExact)
            .collect(Collectors.toList());

        return new ResponseEntity<>(savedUserIds, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Void> updateThirdUser(Long id, UserThirdDto userThirdDto){
        userThirdDto.setId(Math.toIntExact(id));
        UserThird userThird = userThirdDtoMapper.fromDtoToDomain(userThirdDto);
        userThirdService.save(userThird);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
