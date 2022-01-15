package ru.job4j.caraccident.repository.data;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.caraccident.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    /* @Query("select a from Accident a join fetch a.rules")
    List<Accident> findAllWithRules(); */

    @EntityGraph(value = "Accident.rules")
    List<Accident> findAll();

    /* @Query("select a from Accident a join fetch a.rules where a.id = :id")
    Optional<Accident> findByIdWithRules(@Param("id") int id); */

    @EntityGraph(value = "Accident.rules")
    Optional<Accident> findById(int id);
}
