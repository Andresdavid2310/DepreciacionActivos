package com.technicaltest.TechnicalTest.repository;

import com.technicaltest.TechnicalTest.entity.entity.Depreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepreciationRepository extends JpaRepository<Depreciation, String> {

}
