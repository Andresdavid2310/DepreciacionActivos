package com.technicaltest.TechnicalTest.controller;

import com.technicaltest.TechnicalTest.entity.entity.Equipment;
import com.technicaltest.TechnicalTest.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EquipmentControllerTest {

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentController equipmentController;

    private List<Equipment> mockEquipments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockEquipments = new ArrayList<>();
        mockEquipments.add(new Equipment(1L, "Equipment 1", "Description 1", "Name 1", LocalDate.of(2019, 10, 23), 3333.11,null));
        mockEquipments.add(new Equipment(2L, "Equipment 2", "Description 2", "Name 2", LocalDate.of(2018, 9, 04), 4444.55, null));

        when(equipmentService.getAllEquipments()).thenReturn(mockEquipments);

        when(equipmentService.getEquipmentForSerialNumber("1")).thenReturn(mockEquipments.get(0));
        when(equipmentService.getEquipmentForSerialNumber("3")).thenReturn(null);

        when(equipmentService.addEquipment(any(Equipment.class))).thenAnswer((invocation) -> {
            Equipment newEquipment = invocation.getArgument(0);
            newEquipment.setId(3L);
            mockEquipments.add(newEquipment);
            return newEquipment;
        });

        when(equipmentService.updateEquipment(eq("1"), any(Equipment.class))).thenAnswer((invocation) -> {
            Equipment updatedEquipment = invocation.getArgument(1);
            for (Equipment equipment : mockEquipments) {
                if (equipment.getId().equals("1")) {
                    equipment.setName(updatedEquipment.getName());
                    equipment.setDescription(updatedEquipment.getDescription());
                    return equipment;
                }
            }
            return null;
        });

        when(equipmentService.deleteEquipment("1")).thenAnswer((invocation) -> {
            for (Equipment equipment : mockEquipments) {
                if (equipment.getId().equals("1")) {
                    mockEquipments.remove(equipment);
                    return true;
                }
            }
            return false;
        });
    }

    @Test
    public void testGetAllEquipments() {
        List<Equipment> equipments = equipmentController.getAllEquipments();

        assertEquals(mockEquipments.size(), equipments.size());
        assertEquals(mockEquipments, equipments);
    }

    @Test
    public void testGetEquipmentForSerialNumber_ExistingEquipment() {
        ResponseEntity<Object> response = equipmentController.getEquipmentForSerialNumber("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEquipments.get(0), response.getBody());
    }

    @Test
    public void testGetEquipmentForSerialNumber_NonExistingEquipment() {
        ResponseEntity<Object> response = equipmentController.getEquipmentForSerialNumber("3");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Equipment with serial number 3 was not found.", response.getBody());
    }

    @Test
    public void testAddEquipment() {
        Equipment newEquipment = new Equipment(3L, "New Equipment", "New Description", "New Name", LocalDate.of(2020, 02, 10), 222.33, null);

        ResponseEntity<Equipment> response = equipmentController.addEquipment(newEquipment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newEquipment, response.getBody());
    }

    @Test
    public void testDeleteEquipment_NonExistingEquipment() {
        ResponseEntity<String> response = equipmentController.deleteEquipment("3");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Equipment with serial number 3 was not found.", response.getBody());
    }
}
