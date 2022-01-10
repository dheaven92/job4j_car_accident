package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        init();
    }

    public void init() {
        accidents.putAll(Map.of(
                1, new Accident(
                        "Превышение скорости",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, "
                                + "вебмастеру сгенерировать несколько абзацев более менее осмысленного "
                                + "текста рыбы на русском языке, а начинающему оратору отточить навык "
                                + "публичных выступлений в домашних условиях.",
                        "г. Москва, Измайловский Вал ул., 20"
                        ),
                2, new Accident(
                        "Несоблюдение требований знаков или разметки",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, вебмастеру сгенерировать несколько абзацев.",
                        "г. Москва, Кожевническая ул., 7, стр. 1"
                ),
                3, new Accident(
                        "Движение или остановка на выделенной полосе для общественного транспорта",
                        "Сайт рыбатекст поможет дизайнеру, верстальщику, вебмастеру сгенерировать несколько абзацев.",
                        "г. Москва, ул. Красная Пресня, 1"
                )
        ));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
