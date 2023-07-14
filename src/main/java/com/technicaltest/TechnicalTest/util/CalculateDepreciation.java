package com.technicaltest.TechnicalTest.util;
import com.technicaltest.TechnicalTest.entity.Parameters;
import com.technicaltest.TechnicalTest.repository.ParameterRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;
public class CalculateDepreciation {
    private static final String DEPRECIATION_NAME = "percentajeDepreciation";

    public static double calculateDepreciation(double purchaseValue, LocalDate purchaseDate, ParameterRepository parameterRepository) {
        int yearsSincePurchase = calculateYearsSincePurchase(purchaseDate);
        Parameters parameters = parameterRepository.findByName(DEPRECIATION_NAME)
                .orElseThrow(() -> new NoSuchElementException("Parametro no encontrado"));

        // Calcula la depreciación anual
        double depreciationAmount = purchaseValue * Double.parseDouble(parameters.getValueParameter()) * yearsSincePurchase;
        // Resta el monto de depreciación al valor de compra para obtener el valor actualizado
        double updatedValue = purchaseValue - depreciationAmount;

        return updatedValue;
    }
    private static int calculateYearsSincePurchase(LocalDate purchaseDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(purchaseDate, currentDate).getYears();
    }
}
