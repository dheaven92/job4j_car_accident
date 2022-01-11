package ru.job4j.caraccident.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
public class Rule {

    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
