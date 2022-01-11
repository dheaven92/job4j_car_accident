package ru.job4j.caraccident.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
public class Accident {

    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String text;

    private String address;

    private AccidentType type;

    private Set<Rule> rules;

    public void addRule(Rule rule) {
        if (rules == null) {
            rules = new HashSet<>();
        }
        rules.add(rule);
    }
}
