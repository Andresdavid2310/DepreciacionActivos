package com.technicaltest.TechnicalTest.repository;

import com.technicaltest.TechnicalTest.entity.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepository extends JpaRepository<Parameters, String> {
    Optional<Parameters> findByName(String name);
}
