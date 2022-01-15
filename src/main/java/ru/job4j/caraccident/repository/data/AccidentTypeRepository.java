package ru.job4j.caraccident.repository.data;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.caraccident.model.AccidentType;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {

}
