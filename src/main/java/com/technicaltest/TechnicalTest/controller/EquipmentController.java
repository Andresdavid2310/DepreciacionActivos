package com.technicaltest.TechnicalTest.controller;

import com.technicaltest.TechnicalTest.entity.Equipment;
import com.technicaltest.TechnicalTest.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipments")
//@Api(tags = "Product API")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public List<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<Object> getEquipmentForSerialNumber(@PathVariable String serialNumber) {
        Equipment equipment = equipmentService.getEquipmentForSerialNumber(serialNumber);
        if (equipment != null) {
            return ResponseEntity.ok(equipment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment with serial number " + serialNumber + " was not found.");
        }
    }

    @PostMapping
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
        Equipment newEquipment = equipmentService.addEquipment(equipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEquipment);
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable String serialNumber, @RequestBody Equipment equipment) {
        Equipment updateEquipment = equipmentService.updateEquipment(serialNumber, equipment);
        return ResponseEntity.ok(updateEquipment);
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<String> deleteEquipment(@PathVariable String serialNumber) {
        boolean deleted = equipmentService.deleteEquipment(serialNumber);
        if (deleted) {
            return ResponseEntity.ok("Equipment with serial number " + serialNumber + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment with serial number " + serialNumber + " was not found.");
        }
    }
}

