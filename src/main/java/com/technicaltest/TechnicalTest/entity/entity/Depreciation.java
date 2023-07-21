package com.technicaltest.TechnicalTest.entity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Depreciation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Depreciation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int yearDepreciation;
    private double depreciationPurchaseValue;
    private String serialNumber;

    @ManyToOne()
    @JoinColumn(name = "equipment_serialNumber")
    @JsonBackReference
    private Equipment equipment;

    public Depreciation(long l, int i, double v, Equipment equipment) {
    }
}
