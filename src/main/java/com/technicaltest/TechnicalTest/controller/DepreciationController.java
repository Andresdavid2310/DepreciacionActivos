package com.technicaltest.TechnicalTest.controller;

import com.technicaltest.TechnicalTest.entity.entity.Depreciation;
import com.technicaltest.TechnicalTest.entity.dto.DepreciationDto;
import com.technicaltest.TechnicalTest.entity.entity.Equipment;
import com.technicaltest.TechnicalTest.service.DepreciationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/depreciation")
@Api(tags = "Calculate depreciation controller for API")
public class DepreciationController {

    @Autowired
    private DepreciationService depreciationService;

    @PostMapping
    public ResponseEntity<Depreciation> calculateDepreciationForYear(@RequestBody DepreciationDto depreciationDto) {
        Depreciation newDepreciation = depreciationService.calculateDepreciationPerYear(depreciationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDepreciation);
    }

    @GetMapping("/equipment")
    public ResponseEntity<Equipment> getEquipmentBySerialNumber(@RequestParam String serialNumber) {
        Optional<Equipment> optionalEquipment = depreciationService.findEquipmentBySerialNumberAndDepreciation(serialNumber);

        return optionalEquipment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<Depreciation> getAllDepreciation() {
        return depreciationService.getAllDepreciation();
    }
}

