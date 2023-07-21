package com.technicaltest.TechnicalTest.controller;

import com.technicaltest.TechnicalTest.entity.entity.RulesDepreciation;
import com.technicaltest.TechnicalTest.service.RuleServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RuleControllerTest {

    @Mock
    private RuleServices ruleServices;

    @InjectMocks
    private RuleController ruleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRules_shouldReturnListOfRules() {
        List<RulesDepreciation> rulesList = List.of(
                new RulesDepreciation(1L, "2022", "2022"),
                new RulesDepreciation(2L, "2023", "2021")
        );
        when(ruleServices.getAllRules()).thenReturn(rulesList);

        List<RulesDepreciation> result = ruleController.getAllRules();

        assertEquals(rulesList, result);
    }

    @Test
    void addRules_shouldReturnNewRule() {
        RulesDepreciation newRule = new RulesDepreciation(1L, "2022", "2022");
        when(ruleServices.addRule(newRule)).thenReturn(newRule);

        ResponseEntity<RulesDepreciation> response = ruleController.addRules(newRule);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newRule, response.getBody());
    }
}
