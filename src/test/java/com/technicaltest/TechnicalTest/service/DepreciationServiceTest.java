package com.technicaltest.TechnicalTest.service;

import com.technicaltest.TechnicalTest.entity.dto.DepreciationDto;
import com.technicaltest.TechnicalTest.entity.entity.Depreciation;
import com.technicaltest.TechnicalTest.entity.entity.Equipment;
import com.technicaltest.TechnicalTest.entity.entity.RulesDepreciation;
import com.technicaltest.TechnicalTest.repository.DepreciationRepository;
import com.technicaltest.TechnicalTest.repository.EquipmentRepository;
import com.technicaltest.TechnicalTest.repository.RuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DepreciationServiceTest {

    @Mock
    private DepreciationRepository depreciationRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private DepreciationService depreciationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateDepreciationPerYear_shouldCalculateDepreciationAndSave() {
        String serialNumberEquipment = "123456";
        String yearOfCalculateDepreciation = "2022";
        String percentageDepreciation = "0.1";

        Equipment equipment = new Equipment(1L, serialNumberEquipment, "Description", "Name", LocalDate.now(), 1000.0, null);
        RulesDepreciation rulesDepreciation = new RulesDepreciation(1L, yearOfCalculateDepreciation, percentageDepreciation);

        when(equipmentRepository.findBySerialNumber(serialNumberEquipment)).thenReturn(Optional.of(equipment));
        when(ruleRepository.findByYearDepreciation(yearOfCalculateDepreciation)).thenReturn(Optional.of(rulesDepreciation));
        when(depreciationRepository.save(any(Depreciation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        DepreciationDto depreciationDto = new DepreciationDto(serialNumberEquipment, yearOfCalculateDepreciation);
        double expectedDepreciationValue = equipment.getPurchaseValue() * Double.parseDouble(percentageDepreciation);

        Depreciation result = depreciationService.calculateDepreciationPerYear(depreciationDto);

        assertEquals(serialNumberEquipment, result.getEquipment().getSerialNumber());
        assertEquals(Integer.parseInt(yearOfCalculateDepreciation), result.getYearDepreciation());
        assertEquals(expectedDepreciationValue, result.getDepreciationPurchaseValue());
    }

    @Test
    void calculateDepreciationPerYear_shouldThrowNoSuchElementExceptionWhenEquipmentNotFound() {
        String serialNumberEquipment = "123456";
        String yearOfCalculateDepreciation = "2022";

        when(equipmentRepository.findBySerialNumber(serialNumberEquipment)).thenReturn(Optional.empty());

        DepreciationDto depreciationDto = new DepreciationDto(serialNumberEquipment, yearOfCalculateDepreciation);

        try {
            depreciationService.calculateDepreciationPerYear(depreciationDto);
        } catch (NoSuchElementException e) {
            assertEquals("Equipo no encontrado", e.getMessage());
        }
    }

    @Test
    void calculateDepreciationPerYear_shouldThrowNoSuchElementExceptionWhenRuleNotFound() {
        String serialNumberEquipment = "123456";
        String yearOfCalculateDepreciation = "2022";

        Equipment equipment = new Equipment(1L, serialNumberEquipment, "Description", "Name", LocalDate.now(), 1000.0, null);
        when(equipmentRepository.findBySerialNumber(serialNumberEquipment)).thenReturn(Optional.of(equipment));
        when(ruleRepository.findByYearDepreciation(yearOfCalculateDepreciation)).thenReturn(Optional.empty());

        DepreciationDto depreciationDto = new DepreciationDto(serialNumberEquipment, yearOfCalculateDepreciation);

        try {
            depreciationService.calculateDepreciationPerYear(depreciationDto);
        } catch (NoSuchElementException e) {
            assertEquals("Rule not found", e.getMessage());
        }
    }

    @Test
    void findEquipmentBySerialNumberAndDepreciation_shouldReturnEquipmentWithDepreciation() {
        String serialNumberEquipment = "123456";

        Equipment equipment = new Equipment(1L, serialNumberEquipment, "Description", "Name", LocalDate.now(), 1000.0,null);
        Depreciation depreciation = new Depreciation(1L, 2022, 100.0, equipment);

        when(equipmentRepository.findEquipmentBySerialNumberAndDepreciation(anyString())).thenReturn(Optional.of(equipment));

        Optional<Equipment> result = depreciationService.findEquipmentBySerialNumberAndDepreciation(serialNumberEquipment);

        assertEquals(Optional.of(equipment), result);
        assertEquals(1, result.get().getDepreciation().size());
    }

    @Test
    void findEquipmentBySerialNumberAndDepreciation_shouldReturnEmptyOptionalWhenEquipmentNotFound() {
        String serialNumberEquipment = "123456";

        when(equipmentRepository.findEquipmentBySerialNumberAndDepreciation(anyString())).thenReturn(Optional.empty());

        Optional<Equipment> result = depreciationService.findEquipmentBySerialNumberAndDepreciation(serialNumberEquipment);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void getAllDepreciation_shouldReturnListOfDepreciation() {
        List<Depreciation> depreciationList = new ArrayList<>();
        depreciationList.add(new Depreciation(1L, 2022, 100.0, null));
        depreciationList.add(new Depreciation(2L, 2023, 150.0, null));

        when(depreciationRepository.findAll()).thenReturn(depreciationList);

        List<Depreciation> result = depreciationService.getAllDepreciation();

        assertEquals(depreciationList, result);
    }
}
