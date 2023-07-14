package com.technicaltest.TechnicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serialNumber", nullable = false)
    private String serialNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "purchaseDate", nullable = false)
    private String purchaseDate;

    @Column(name = "purchaseValue", nullable = false)
    private Double purchaseValue;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Depreciation> depreciations;
}
