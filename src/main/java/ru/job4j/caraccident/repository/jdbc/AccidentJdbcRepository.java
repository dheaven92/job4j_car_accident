package ru.job4j.caraccident.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class AccidentJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Accident> findAllAccidents() {
        return jdbcTemplate.query("select * from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                });
    }

    public Accident findAccidentById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                },
                id
        );
    }

    public Accident createAccident(Accident accident) {
        String query = "insert into accident (name, description, address, type_id) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[] {"id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                },
                keyHolder
        );
        accident.setId(keyHolder.getKey().intValue());
        return accident;
    }

    public Accident updateAccident(Accident accident) {
        jdbcTemplate.update(
                "update accident set name = ?, description = ?, address = ?, type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        return accident;
    }

    public List<AccidentType> findAllAccidentTypes() {
        return jdbcTemplate.query("select * from type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public AccidentType findAccidentTypeById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from type where id = ?",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                },
                id
        );
    }

    public List<Rule> findAllRules() {
        return jdbcTemplate.query("select * from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<Rule> findAllRulesByAccidentId(int accidentId) {
        return jdbcTemplate.query("select * from rule r join accident_rule ar on r.id = ar.rule_id where ar.accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                accidentId
            );
    }

    public Rule findRuleById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from rule where id = ?",
                (rs, row) -> {
                    Rule type = new Rule();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                },
                id
        );
    }

    public void saveAccidentRule(int accidentId, int ruleId) {
        jdbcTemplate.update(
                "insert into accident_rule (accident_id, rule_id) values (?, ?)",
                accidentId,
                ruleId
        );
    }

    public void deleteAccidentRule(int accidentId) {
        jdbcTemplate.update(
                "delete from accident_rule where accident_id = ?",
                accidentId
        );
    }
}
