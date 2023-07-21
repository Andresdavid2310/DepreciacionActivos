package com.technicaltest.TechnicalTest.repository;

import com.technicaltest.TechnicalTest.entity.entity.RulesDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuleRepository extends JpaRepository<RulesDepreciation, String> {
    Optional<RulesDepreciation> findByYearDepreciation(String yearDepreciation);

}
