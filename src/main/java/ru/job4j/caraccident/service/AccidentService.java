package ru.job4j.caraccident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {

    @Autowired
    private AccidentMem accidentMem;

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidentMem.findAllAccidents());
    }

    public Accident findAccidentById(int id) {
        Accident accident = accidentMem.findAccidentById(id);
        if (accident == null) {
            throw new IllegalArgumentException("Could not find an accident with id " + id);
        }
        return accident;
    }

    public void saveAccident(Accident accident) {
        accidentMem.saveAccident(accident);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(accidentMem.findAllAccidentTypes());
    }

    public List<Rule> findAllRules() {
        return new ArrayList<>(accidentMem.findAllRules());
    }

    public void setRulesToAccident(Set<Integer> ruleIds, Accident accident) {
        ruleIds.forEach(id -> {
            Rule rule = accidentMem.findRuleById(id);
            if (rule != null) {
                accident.addRule(rule);
            }
        });
    }
}
