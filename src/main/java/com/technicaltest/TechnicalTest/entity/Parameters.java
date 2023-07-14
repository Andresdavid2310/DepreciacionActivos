package com.technicaltest.TechnicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Parameters {

    private String name;

    private String Value;
}
