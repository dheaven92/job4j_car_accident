package ru.job4j.caraccident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.repository.data.AccidentRepository;
import ru.job4j.caraccident.repository.data.AccidentTypeRepository;
import ru.job4j.caraccident.repository.data.RuleRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class AccidentService {

    @Autowired
    private AccidentRepository accidentRepository;

    @Autowired
    private AccidentTypeRepository accidentTypeRepository;

    @Autowired
    private RuleRepository ruleRepository;

    public List<Accident> findAllAccidents() {
        return accidentRepository.findAll();
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find an accident with id " + id));
    }

    @Transactional
    public void saveAccident(Accident accident, String[] rulesIds) {
        setRulesToAccident(rulesIds, accident);
        accidentRepository.save(accident);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return (List<AccidentType>) accidentTypeRepository.findAll();
    }

    public List<Rule> findAllRules() {
        return (List<Rule>) ruleRepository.findAll();
    }

    private void setRulesToAccident(String[] ruleIds, Accident accident) {
        Arrays.stream(ruleIds)
                .map(Integer::parseInt)
                .forEach(id -> ruleRepository.findById(id).ifPresent(accident::addRule));
    }
}
