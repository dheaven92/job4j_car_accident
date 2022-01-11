package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger(0);
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public AccidentMem() {
        init();
    }

    private void init() {
        accidentTypes.putAll(Map.of(
                1, new AccidentType(1, "Две машины"),
                2, new AccidentType(2, "Машина и человек"),
                3, new AccidentType(3, "Машина и велосипед")
        ));

        accidents.putAll(Map.of(
                1, new Accident(
                        1,
                        "Превышение скорости",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, "
                                + "вебмастеру сгенерировать несколько абзацев более менее осмысленного "
                                + "текста рыбы на русском языке, а начинающему оратору отточить навык "
                                + "публичных выступлений в домашних условиях.",
                        "г. Москва, Измайловский Вал ул., 20",
                        accidentTypes.get(1)
                        ),
                2, new Accident(
                        2,
                        "Несоблюдение требований знаков или разметки",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, вебмастеру сгенерировать несколько абзацев.",
                        "г. Москва, Кожевническая ул., 7, стр. 1",
                        accidentTypes.get(1)
                ),
                3, new Accident(
                        3,
                        "Движение или остановка на выделенной полосе для общественного транспорта",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, вебмастеру сгенерировать несколько абзацев.",
                        "г. Москва, ул. Красная Пресня, 1",
                        accidentTypes.get(2)
                )
        ));
        accidentId.set(3);
    }

    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentId.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return accidentTypes.values();
    }
}
