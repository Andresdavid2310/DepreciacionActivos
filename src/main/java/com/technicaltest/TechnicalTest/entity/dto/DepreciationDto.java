package com.technicaltest.TechnicalTest.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepreciationDto {
    private String yearOfCalculateDepreciation;
    private String serialNumberEquipment;
}
