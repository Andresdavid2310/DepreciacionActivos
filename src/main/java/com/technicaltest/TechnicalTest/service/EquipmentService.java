package com.technicaltest.TechnicalTest.service;

import com.technicaltest.TechnicalTest.entity.entity.Equipment;
import com.technicaltest.TechnicalTest.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentForSerialNumber(String serialNumber) {
        Equipment equipment = equipmentRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new NoSuchElementException("Equipo no encontrado"));
        return  equipment;
    }

    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(String serialNumber, Equipment equipment) {
        Equipment equipmentExists = getEquipmentForSerialNumber(serialNumber);
        equipmentExists.setDescription(equipment.getDescription());
        equipmentExists.setName(equipment.getName());
        equipmentExists.setPurchaseDate(equipment.getPurchaseDate());
        equipmentExists.setPurchaseValue(equipment.getPurchaseValue());
        return equipmentRepository.save(equipmentExists);
    }

    public boolean deleteEquipment(String serialNumber) {
        Equipment equipoExistente = getEquipmentForSerialNumber(serialNumber);
        equipmentRepository.delete(equipoExistente);
        return true;
    }
}
