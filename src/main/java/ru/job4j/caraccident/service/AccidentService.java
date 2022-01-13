package ru.job4j.caraccident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void saveAccident(Accident accident, String[] rulesIds) {
        setTypeToAccident(accident.getType().getId(), accident);
        setRulesToAccident(rulesIds, accident);
        accidentMem.saveAccident(accident);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(accidentMem.findAllAccidentTypes());
    }

    public List<Rule> findAllRules() {
        return new ArrayList<>(accidentMem.findAllRules());
    }

    private void setTypeToAccident(int typeId, Accident accident) {
        AccidentType type = accidentMem.findAccidentTypeById(typeId);
        if (type != null) {
            accident.setType(type);
        }
    }

    private void setRulesToAccident(String[] ruleIds, Accident accident) {
        Set<Integer> ids = Arrays.stream(ruleIds)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        ids.forEach(id -> {
            Rule rule = accidentMem.findRuleById(id);
            if (rule != null) {
                accident.addRule(rule);
            }
        });
    }
}
