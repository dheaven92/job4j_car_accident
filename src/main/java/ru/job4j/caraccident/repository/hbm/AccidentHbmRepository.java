package ru.job4j.caraccident.repository.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;

import java.util.List;

@Repository
public class AccidentHbmRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Accident> findAllAccidents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select distinct a from Accident a left join fetch a.rules", Accident.class)
                    .getResultList();
        }
    }

    public Accident findAccidentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Accident> query = session.createQuery(
                    "select distinct a from Accident a left join fetch a.rules where a.id = :id",
                    Accident.class
            );
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    public Accident saveAccident(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(accident);
            transaction.commit();
            return accident;
        }
    }

    public List<AccidentType> findAllAccidentTypes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select t from AccidentType t", AccidentType.class)
                    .getResultList();
        }
    }

    public List<Rule> findAllRules() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select r from Rule r", Rule.class)
                    .getResultList();
        }
    }

    public Rule findRuleById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Rule.class, id);
        }
    }
}
