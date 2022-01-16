package ru.job4j.caraccident.repository.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;

import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHbmRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Accident> findAllAccidents() {
        return executeTransaction(session -> session
                .createQuery("select distinct a from Accident a left join fetch a.rules", Accident.class)
                .getResultList());
    }

    public Accident findAccidentById(int id) {
        return executeTransaction(session -> session
                .createQuery("select distinct a from Accident a left join fetch a.rules where a.id = :id", Accident.class)
                .setParameter("id", id)
                .getSingleResult()
        );
    }

    public Accident saveAccident(Accident accident) {
        return executeTransaction(session -> {
            session.saveOrUpdate(accident);
            return accident;
        });
    }

    public List<AccidentType> findAllAccidentTypes() {
        return executeTransaction(session -> session
                .createQuery("select t from AccidentType t", AccidentType.class)
                .getResultList());
    }

    public List<Rule> findAllRules() {
        return executeTransaction(session -> session
                .createQuery("select r from Rule r", Rule.class)
                .getResultList());
    }

    public Rule findRuleById(int id) {
        return executeTransaction(session -> session.get(Rule.class, id));
    }

    private <T> T executeTransaction(Function<Session, T> query) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            T result = query.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
