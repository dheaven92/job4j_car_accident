package ru.job4j.caraccident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    @Autowired
    private AccidentMem accidentMem;

    public List<Accident> findAll() {
        return new ArrayList<>(accidentMem.findAll());
    }

    public Accident findById(int id) {
        Accident accident = accidentMem.findById(id);
        if (accident == null) {
            throw new IllegalArgumentException("Could not find an accident with id " + id);
        }
        return accident;
    }

    public void save(Accident accident) {
        accidentMem.save(accident);
    }
}
