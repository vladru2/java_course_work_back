package ru.rsatu.hibernatetutorial.repository;

import ru.rsatu.hibernatetutorial.pojo.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class LoginRepository {

    @Inject
    EntityManager entityManager;

    public List<User> getUser(String name) {
        Query query = entityManager.createQuery("select u from User u where username =:uname", User.class);
        query.setParameter("uname", name);
        return query.getResultList();
    }
}