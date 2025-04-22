package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.service.VehicleThirdService;
import com.dev.insurance_users.generated.api.ThirdVehiclesApi;
import com.dev.insurance_users.generated.model.VehicleThirdDto;
import com.dev.insurance_users.infrastructure.rest.mapper.VehicleThirdDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class VehicleThirdController implements ThirdVehiclesApi {

    private final VehicleThirdService vehicleThirdService;
    private final VehicleThirdDtoMapper vehicleThirdDtoMapper;

    @Override
    public ResponseEntity<Void> deleteThirdVehicleById(Long id) {
        vehicleThirdService.deleteVehicleById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<VehicleThirdDto>> getAllThirdVehicles(){
        List<VehicleThird> vehicles = vehicleThirdService.findAll();
        List<VehicleThirdDto> vehiclesDto = vehicles.stream()
                .map(vehicleThirdDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(vehiclesDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VehicleThirdDto> getThirdVehicleById(Long id){
        try {
            Optional<VehicleThird> vehicleOpt = vehicleThirdService.findById(id);
            return vehicleOpt.map(vehicleThird ->
                    new ResponseEntity<>(vehicleThirdDtoMapper.fromDomainToDto(vehicleThird), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> saveThirdVehicle(VehicleThirdDto vehicleThirdDto){
        VehicleThird vehicle = vehicleThirdDtoMapper.fromDtoToDomain(vehicleThirdDto);
        vehicleThirdService.save(vehicle);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

 //TODO : FIX ID
    @Override
    public ResponseEntity<Void> updateThirdVehicle(Long id ,VehicleThirdDto vehicleThirdDto){
        try {
            VehicleThird vehicle = vehicleThirdDtoMapper.fromDtoToDomain(vehicleThirdDto);
            vehicle.setId(id);
            vehicleThirdService.save(vehicle);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<VehicleThirdDto> findByMatriculaThird(String matricula) {
        Optional<VehicleThird> vehicleOpt = vehicleThirdService.findByMatriculaThird(matricula);
        return vehicleOpt
            .map(vehicleThirdDtoMapper::fromDomainToDto)
            .map(vehicleDto -> new ResponseEntity<>(vehicleDto, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
