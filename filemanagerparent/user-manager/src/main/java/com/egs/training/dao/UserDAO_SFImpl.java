package com.egs.training.dao;

import com.egs.training.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO_SFImpl implements UserDAO {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {

        return sessionFactory.getCurrentSession().
                createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUser(Long id) {

        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void saveUser(User user) {

        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void deleteUser(Long id) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery("DELETE FROM User WHERE id=:id");
        theQuery.setParameter("id", id);

        theQuery.executeUpdate();
    }

}
