package com.dev.insurance_users.infrastructure.rest.controller;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.service.VehicleThirdService;
import com.dev.insurance_users.generated.api.ThirdVehiclesApi;
import com.dev.insurance_users.generated.model.ThirdPartyVehicleDto;
import com.dev.insurance_users.generated.model.ThirdPartyVehiclesWrapperDto;
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
        List<ThirdPartyVehicleDto> vehiclesDto = vehicles.stream()
                .map(vehicleThirdDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        wrapper.setVehicles(vehiclesDto);
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ThirdPartyVehicleDto> getThirdVehicleById(Long id){
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
    public ResponseEntity<Void> saveThirdVehicle(ThirdPartyVehiclesWrapperDto vehicleThirdDto){
        var vehiculos = vehicleThirdDto.getVehicles();
        var vehiculosDomain = vehiculos.stream().map(element ->  vehicleThirdDtoMapper.fromDtoToDomain(element)).collect(Collectors.toList());

        vehicleThirdService.save(vehiculosDomain);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

 //TODO : FIX ID
    @Override
    public ResponseEntity<Void> updateThirdVehicle(Long id ,ThirdPartyVehicleDto vehicleThirdDto){
        VehicleThird vehicle = vehicleThirdDtoMapper.fromDtoToDomain(vehicleThirdDto);
        vehicle.setId(id);
        vehicleThirdService.save(List.of(vehicle));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ThirdPartyVehicleDto> findByMatriculaThird(String matricula) {
        Optional<VehicleThird> vehicleOpt = vehicleThirdService.findByMatriculaThird(matricula);
        return vehicleOpt
            .map(vehicleThirdDtoMapper::fromDomainToDto)
            .map(vehicleDto -> new ResponseEntity<>(vehicleDto, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
