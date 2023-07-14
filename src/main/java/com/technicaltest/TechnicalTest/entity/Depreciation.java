package com.technicaltest.TechnicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Depreciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;

    private String name;

    private double depreciation;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
