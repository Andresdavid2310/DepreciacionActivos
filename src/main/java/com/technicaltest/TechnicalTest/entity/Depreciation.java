package com.technicaltest.TechnicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Depreciation")
@Getter
@Setter
public class Depreciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int yearValue;

    private String name;

    private double depreciation;

    /*@ManyToOne
    @JoinColumn(name = "Equipment_id")
    private Equipment equipment;*/
}
