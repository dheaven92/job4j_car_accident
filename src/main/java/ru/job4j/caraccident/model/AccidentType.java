package ru.job4j.caraccident.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@Table(name = "type")
@Entity
public class AccidentType {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private int id;

    private String name;
}
