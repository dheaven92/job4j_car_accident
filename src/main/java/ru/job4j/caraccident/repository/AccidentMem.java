package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger(0);

    public AccidentMem() {
        init();
    }

    private void init() {
        accidents.putAll(Map.of(
                1, new Accident(
                        1,
                        "Превышение скорости",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, "
                                + "вебмастеру сгенерировать несколько абзацев более менее осмысленного "
                                + "текста рыбы на русском языке, а начинающему оратору отточить навык "
                                + "публичных выступлений в домашних условиях.",
                        "г. Москва, Измайловский Вал ул., 20"
                        ),
                2, new Accident(
                        2,
                        "Несоблюдение требований знаков или разметки",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, вебмастеру сгенерировать несколько абзацев.",
                        "г. Москва, Кожевническая ул., 7, стр. 1"
                ),
                3, new Accident(
                        3,
                        "Движение или остановка на выделенной полосе для общественного транспорта",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, вебмастеру сгенерировать несколько абзацев.",
                        "г. Москва, ул. Красная Пресня, 1"
                )
        ));
        accidentId.set(3);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentId.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }
}
