package com.technicaltest.TechnicalTest.service;

import com.technicaltest.TechnicalTest.entity.Equipment;
import com.technicaltest.TechnicalTest.repository.EquipmentRepository;
import com.technicaltest.TechnicalTest.repository.ParameterRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private ParameterRepository parameterRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEquipments_ReturnsListOfEquipments() {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Equipment());
        when(equipmentRepository.findAll()).thenReturn(equipmentList);

        List<Equipment> result = equipmentService.getAllEquipments();

        assertEquals(equipmentList, result);
    }

    @Test
    void getEquipmentForSerialNumber_ExistingSerialNumber_ReturnsEquipment() {
        String serialNumber = "123";
        Equipment equipment = new Equipment();
        when(equipmentRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.of(equipment));

        Equipment result = equipmentService.getEquipmentForSerialNumber(serialNumber);

        assertEquals(equipment, result);
    }

    @Test
    void getEquipmentForSerialNumber_NonExistingSerialNumber_ThrowsNoSuchElementException() {
        String serialNumber = "123";
        when(equipmentRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            equipmentService.getEquipmentForSerialNumber(serialNumber);
        });
    }

    @Test
    void updateEquipment_ExistingSerialNumber_ReturnsUpdatedEquipment() {
        String serialNumber = "123";
        Equipment existingEquipment = new Equipment();
        existingEquipment.setPurchaseValue(100.0);
        existingEquipment.setPurchaseDate(LocalDate.now());
        when(equipmentRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.of(existingEquipment));

        Equipment updatedEquipment = new Equipment();
        updatedEquipment.setPurchaseValue(200.0);
        updatedEquipment.setPurchaseDate(LocalDate.now().minusYears(1));
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(updatedEquipment);

        Equipment result = equipmentService.updateEquipment(serialNumber, updatedEquipment);

        assertNotNull(result);
        assertEquals(updatedEquipment.getPurchaseValue(), result.getPurchaseValue());
        assertEquals(updatedEquipment.getPurchaseDate(), result.getPurchaseDate());
    }

    @Test
    void updateEquipment_NonExistingSerialNumber_ThrowsNoSuchElementException() {
        String serialNumber = "123";
        when(equipmentRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.empty());

        Equipment updatedEquipment = new Equipment();
        updatedEquipment.setPurchaseValue(200.0);
        updatedEquipment.setPurchaseDate(LocalDate.now().minusYears(1));

        assertThrows(NoSuchElementException.class, () -> {
            equipmentService.updateEquipment(serialNumber, updatedEquipment);
        });
    }

    @Test
    void deleteEquipment_ExistingSerialNumber_DeletesEquipmentAndReturnsTrue() {
        String serialNumber = "123";
        Equipment existingEquipment = new Equipment();
        when(equipmentRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.of(existingEquipment));

        boolean result = equipmentService.deleteEquipment(serialNumber);

        assertTrue(result);
        verify(equipmentRepository, times(1)).delete(existingEquipment);
    }

    @Test
    void deleteEquipment_NonExistingSerialNumber_ThrowsNoSuchElementException() {
        String serialNumber = "123";
        when(equipmentRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            equipmentService.deleteEquipment(serialNumber);
        });
        verify(equipmentRepository, never()).delete(any(Equipment.class));
    }
}
