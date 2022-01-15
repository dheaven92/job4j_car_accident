package ru.job4j.caraccident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.repository.hbm.AccidentHbmRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    @Autowired
    private AccidentHbmRepository accidentRepository;

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidentRepository.findAllAccidents());
    }

    public Accident findAccidentById(int id) {
        Accident accident = accidentRepository.findAccidentById(id);
        if (accident == null) {
            throw new IllegalArgumentException("Could not find an accident with id " + id);
        }
        return accident;
    }

    @Transactional
    public void saveAccident(Accident accident, String[] rulesIds) {
        setRulesToAccident(rulesIds, accident);
        accidentRepository.saveAccident(accident);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(accidentRepository.findAllAccidentTypes());
    }

    public List<Rule> findAllRules() {
        return new ArrayList<>(accidentRepository.findAllRules());
    }

    private void setRulesToAccident(String[] ruleIds, Accident accident) {
        Set<Integer> ids = Arrays.stream(ruleIds)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        ids.forEach(id -> {
            Rule rule = accidentRepository.findRuleById(id);
            if (rule != null) {
                accident.addRule(rule);
            }
        });
    }
}
