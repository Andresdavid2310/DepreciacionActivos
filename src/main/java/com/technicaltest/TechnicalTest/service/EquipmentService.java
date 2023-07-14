package com.technicaltest.TechnicalTest.service;

import com.technicaltest.TechnicalTest.entity.Equipment;
import com.technicaltest.TechnicalTest.repository.EquipmentRepository;
import com.technicaltest.TechnicalTest.repository.ParameterRepository;
import com.technicaltest.TechnicalTest.util.CalculateDepreciation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ParameterRepository parameterRepository;


    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentForSerialNumber(String serialNumber) {
        return equipmentRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new NoSuchElementException("Equipo no encontrado"));
    }

    public Equipment addEquipment(Equipment equipment) {
        equipment.setDepreciationPurchaseValue(CalculateDepreciation.calculateDepreciation(equipment.getPurchaseValue(),equipment.getPurchaseDate(),parameterRepository));
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(String serialNumber, Equipment equipment) {
        Equipment equipmentExists = getEquipmentForSerialNumber(serialNumber);
        equipmentExists.setDescription(equipment.getDescription());
        equipmentExists.setName(equipment.getName());
        equipmentExists.setPurchaseDate(equipment.getPurchaseDate());
        equipmentExists.setPurchaseValue(equipment.getPurchaseValue());
        if(!equipmentExists.getPurchaseDate().equals(equipment.getPurchaseDate()) ||
        !equipmentExists.getPurchaseValue().equals(equipment.getPurchaseValue())){
            equipmentExists.setDepreciationPurchaseValue(CalculateDepreciation.calculateDepreciation(equipment.getPurchaseValue(),equipment.getPurchaseDate(),parameterRepository));
        }
        return equipmentRepository.save(equipmentExists);
    }

    public boolean deleteEquipment(String serialNumber) {
        Equipment equipoExistente = getEquipmentForSerialNumber(serialNumber);
        equipmentRepository.delete(equipoExistente);
        return true;
    }
}
