package com.technicaltest.TechnicalTest.service;

import com.technicaltest.TechnicalTest.entity.entity.Depreciation;
import com.technicaltest.TechnicalTest.entity.dto.DepreciationDto;
import com.technicaltest.TechnicalTest.entity.entity.Equipment;
import com.technicaltest.TechnicalTest.entity.entity.RulesDepreciation;
import com.technicaltest.TechnicalTest.repository.DepreciationRepository;
import com.technicaltest.TechnicalTest.repository.EquipmentRepository;
import com.technicaltest.TechnicalTest.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class DepreciationService {

    @Autowired
    private DepreciationRepository depreciationRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private RuleRepository ruleRepository;

    public Depreciation calculateDepreciationPerYear(DepreciationDto depreciationDto) {
        Equipment equipment = equipmentRepository.findBySerialNumber(depreciationDto.getSerialNumberEquipment())
                .orElseThrow(() -> new NoSuchElementException("Equipo no encontrado"));;
        RulesDepreciation rulesDepreciation = ruleRepository.findByYearDepreciation(depreciationDto.getYearOfCalculateDepreciation())
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));
        Depreciation depreciation = new Depreciation();
        depreciation.setSerialNumber(equipment.getSerialNumber());
        depreciation.setYearDepreciation(Integer.parseInt(depreciationDto.getYearOfCalculateDepreciation()));
        depreciation.setDepreciationPurchaseValue(equipment.getPurchaseValue()*Double.parseDouble(rulesDepreciation.getPercentageDepreciation()));
        depreciation.setEquipment(equipment);

        // Guardar la depreciación
        Depreciation savedDepreciation = depreciationRepository.save(depreciation);

        // Agregar la depreciación al equipo (si es necesario)
        List<Depreciation> depreciations = equipment.getDepreciation();
        if (depreciations == null) {
            depreciations = new ArrayList<>();
            equipment.setDepreciation(depreciations);
        }
        depreciations.add(savedDepreciation);
        return depreciationRepository.save(depreciation);
    }

    public Optional<Equipment> findEquipmentBySerialNumberAndDepreciation(String serialNumber) {
        return equipmentRepository.findEquipmentBySerialNumberAndDepreciation(serialNumber);
    }

    public List<Depreciation> getAllDepreciation(){
        return depreciationRepository.findAll();
    }
}
