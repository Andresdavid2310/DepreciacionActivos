package com.technicaltest.TechnicalTest.service;

import com.technicaltest.TechnicalTest.entity.entity.RulesDepreciation;
import com.technicaltest.TechnicalTest.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RuleServices {

    @Autowired
    private RuleRepository ruleRepository;

    public List<RulesDepreciation> getAllRules() {
        return ruleRepository.findAll();
    }

    public RulesDepreciation addRule(RulesDepreciation rule) {
        return ruleRepository.save(rule);
    }

}
