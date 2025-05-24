package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.service.VehicleThirdService;
import com.dev.insurance_users.generated.api.ThirdVehiclesApi;

import com.dev.insurance_users.generated.model.ThirdPartyVehiclesWrapperDto;
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
    public ResponseEntity<ThirdPartyVehiclesWrapperDto> getAllThirdVehicles(){
        List<VehicleThird> vehicles = vehicleThirdService.findAll();
        var wrapper = new ThirdPartyVehiclesWrapperDto();
        List<VehicleThirdDto> vehiclesDto = vehicles.stream()
                .map(vehicleThirdDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        wrapper.setVehicles(vehiclesDto);
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
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
    public ResponseEntity<List<Integer>> saveThirdVehicle(ThirdPartyVehiclesWrapperDto vehicleThirdDto) {
        if (vehicleThirdDto == null || vehicleThirdDto.getVehicles() == null || vehicleThirdDto.getVehicles().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<VehicleThird> vehiclesToSave = vehicleThirdDto.getVehicles().stream()
                .map(vehicleThirdDtoMapper::fromDtoToDomain)
                .collect(Collectors.toList());

        List<Integer> savedVehicles = vehiclesToSave.stream()
                .map(vehicleThirdService::save)
                .collect(Collectors.toList());

        if (savedVehicles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<Integer> savedVehicleIds = savedVehicles.stream()
                .map(Math::toIntExact)
                .collect(Collectors.toList());

        return new ResponseEntity<>(savedVehicleIds, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateThirdVehicle(Long id ,VehicleThirdDto vehicleThirdDto){
        VehicleThird vehicle = vehicleThirdDtoMapper.fromDtoToDomain(vehicleThirdDto);
        vehicle.setId(id);
        vehicleThirdService.save(vehicle);
        return new ResponseEntity<>(HttpStatus.OK);
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
