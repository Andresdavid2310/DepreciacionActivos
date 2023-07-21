package com.technicaltest.TechnicalTest.controller;

import com.technicaltest.TechnicalTest.entity.dto.DepreciationDto;
import com.technicaltest.TechnicalTest.entity.entity.Depreciation;
import com.technicaltest.TechnicalTest.entity.entity.Equipment;
import com.technicaltest.TechnicalTest.service.DepreciationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DepreciationControllerTest {

    @Mock
    private DepreciationService depreciationService;

    @InjectMocks
    private DepreciationController depreciationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateDepreciationForYear_shouldReturnNewDepreciation() {
        DepreciationDto depreciationDto = new DepreciationDto("123445663", "2023");;
        Depreciation newDepreciation = new Depreciation(1L, 2020, 123445664, "2022", null);;
        when(depreciationService.calculateDepreciationPerYear(any(DepreciationDto.class))).thenReturn(newDepreciation);

        ResponseEntity<Depreciation> response = depreciationController.calculateDepreciationForYear(depreciationDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newDepreciation, response.getBody());
    }

    @Test
    void getEquipmentBySerialNumber_shouldReturnEquipmentIfExists() {
        String serialNumber = "123445663";
        Equipment equipment = new Equipment(1L, serialNumber, "Escritorio grande", "Escritorio de oficina grande", LocalDate.of(2020, 7, 18), 2000000.0,null);
        when(depreciationService.findEquipmentBySerialNumberAndDepreciation(serialNumber)).thenReturn(Optional.of(equipment));

        ResponseEntity<Equipment> response = depreciationController.getEquipmentBySerialNumber(serialNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipment, response.getBody());
    }

    @Test
    void getEquipmentBySerialNumber_shouldReturnNotFoundIfEquipmentDoesNotExist() {
        String serialNumber = "nonExistentSerialNumber";
        when(depreciationService.findEquipmentBySerialNumberAndDepreciation(serialNumber)).thenReturn(Optional.empty());

        ResponseEntity<Equipment> response = depreciationController.getEquipmentBySerialNumber(serialNumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllDepreciation_shouldReturnListOfDepreciation() {
        List<Depreciation> depreciationList = List.of(new Depreciation(1L, 2020, 123445664, "2022", null));
        when(depreciationService.getAllDepreciation()).thenReturn(depreciationList);

        List<Depreciation> result = depreciationController.getAllDepreciation();

        assertEquals(depreciationList, result);
    }
}

