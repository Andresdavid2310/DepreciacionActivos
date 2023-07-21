package com.technicaltest.TechnicalTest.controller;

import com.technicaltest.TechnicalTest.entity.entity.RulesDepreciation;
import com.technicaltest.TechnicalTest.service.RuleServices;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
@Api(tags = "Rule controller for API")
public class RuleController {

    @Autowired
    private RuleServices ruleServices;

    @GetMapping
    public List<RulesDepreciation> getAllRules() {
        return ruleServices.getAllRules();
    }

    @PostMapping
    public ResponseEntity<RulesDepreciation> addRules(@RequestBody RulesDepreciation rule) {
        RulesDepreciation newRule = ruleServices.addRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRule);
    }
}

