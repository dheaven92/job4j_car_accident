package ru.job4j.caraccident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.repository.jdbc.AccidentJdbcRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    @Autowired
    private AccidentJdbcRepository accidentRepository;

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

    public void saveAccident(Accident accident, String[] rulesIds) {
        Accident savedAccident = accident.getId() == 0
                ? accidentRepository.createAccident(accident)
                : accidentRepository.updateAccident(accident);
        setRulesToAccident(rulesIds, savedAccident.getId());
    }

    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(accidentRepository.findAllAccidentTypes());
    }

    public List<Rule> findAllRules() {
        return new ArrayList<>(accidentRepository.findAllRules());
    }

    private void setRulesToAccident(String[] ruleIds, int accidentId) {
        accidentRepository.deleteAccidentRule(accidentId);
        Arrays.stream(ruleIds)
                .map(Integer::parseInt)
                .collect(Collectors.toSet())
                .forEach(ruleId -> accidentRepository.saveAccidentRule(accidentId, ruleId));
    }
}
