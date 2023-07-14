package com.technicaltest.TechnicalTest.repository;

import com.technicaltest.TechnicalTest.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    Optional<Equipment> findBySerialNumber(String serialNumber);
}
