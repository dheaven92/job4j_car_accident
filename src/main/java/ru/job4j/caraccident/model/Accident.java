package ru.job4j.caraccident.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "rules")
@Getter
@Setter
@Table(name = "accident")
@NamedEntityGraph(name = "Accident.rules",
        attributeNodes = @NamedAttributeNode("rules")
)
@Entity
public class Accident {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "description")
    private String text;

    private String address;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "accident_rule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<Rule> rules;

    public void addRule(Rule rule) {
        if (rules == null) {
            rules = new HashSet<>();
        }
        rules.add(rule);
    }
}
