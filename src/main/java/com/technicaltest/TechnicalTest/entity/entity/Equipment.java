package com.technicaltest.TechnicalTest.entity.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Equipment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "purchaseDate", nullable = false, updatable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchaseValue", nullable = false)
    private Double purchaseValue;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Depreciation> depreciation;

}
