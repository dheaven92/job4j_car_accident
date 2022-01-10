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
}
