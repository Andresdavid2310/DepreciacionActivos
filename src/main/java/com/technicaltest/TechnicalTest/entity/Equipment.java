package com.technicaltest.TechnicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serialNumber", nullable = false, updatable = false, unique = true)
    private String serialNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "purchaseDate", nullable = false , updatable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchaseValue", nullable = false)
    private Double purchaseValue;

   @Column(name = "depreciationPurchasePValue")
    private Double depreciationPurchaseValue;

    public Equipment(long l, String s, String s1, String s2, String date, double v) {
    }

    public Equipment() {

    }
}
