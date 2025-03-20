package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.service.UserThirdService;
import com.dev.insurance_users.generated.api.ThirdUsersApi;
import com.dev.insurance_users.infrastructure.rest.controller.mapper.UserThirdDtoMapper;
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
    public ResponseEntity<Void> deleteThirdUserById(String id){
        userThirdService.deleteUserById(Long.valueOf(id));
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
    public ResponseEntity<UserThirdDto> findThirdUserById(String id){
        try{
            Long vehicleId = Long.parseLong(id);
            Optional<UserThird> userThirdOpt = userThirdService.findById(vehicleId);
            return userThirdOpt
                    .map(userThird -> new ResponseEntity<>(userThirdDtoMapper.fromDomainToDto(userThird), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> saveThirdUser(UserThirdDto userThirdDto){
        UserThird userThird = userThirdDtoMapper.fromDtoToDomain(userThirdDto);
        userThirdService.save(userThird);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateThirdUser(String id, UserThirdDto userThirdDto){
        try{
            Long userId = Long.parseLong(id);
            userThirdDto.setId(Math.toIntExact(userId));
            UserThird userThird = userThirdDtoMapper.fromDtoToDomain(userThirdDto);
            userThirdService.save(userThird);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
