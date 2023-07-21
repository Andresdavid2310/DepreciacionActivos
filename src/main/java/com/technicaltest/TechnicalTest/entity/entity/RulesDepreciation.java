package com.technicaltest.TechnicalTest.entity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Rules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RulesDepreciation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percentageDepreciation")
    private String percentageDepreciation;

    @Column(name = "yearDepreciation", nullable = false, unique = true)
    private String yearDepreciation;

}
