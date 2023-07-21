package com.technicaltest.TechnicalTest.repository;

import com.technicaltest.TechnicalTest.entity.entity.Equipment;
    import org.springframework.data.jpa.repository.EntityGraph;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface EquipmentRepository extends JpaRepository<Equipment, String> {
        @EntityGraph(attributePaths = "depreciation")
        Optional<Equipment> findBySerialNumber(String serialNumber);
        @Query("SELECT e FROM Equipment e JOIN FETCH e.depreciation WHERE e.serialNumber = :serialNumber")
        Optional<Equipment> findEquipmentBySerialNumberAndDepreciation(@Param("serialNumber") String serialNumber);
    }
