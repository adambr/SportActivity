package cz.muni.fi.pa165.sportactivitymanager.dao.impl;

import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.dao.UserDAO;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Dobes Kuba
 *
 */
public class UserDAOImpl implements UserDAO {

    private EntityManager em;

    public UserDAOImpl() {
    }

    public UserDAOImpl(EntityManager em) {
        if (em == null) {
            throw new NullPointerException();
        }
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void create(User user) {
        if (user == null) {
            throw new NullPointerException("User is Null");
        }
        em.persist(user);
    }

    public User getByID(Long id) {
        if (id == null) {
            throw new NullPointerException("User ID is Null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("User ID must be Positive");
        }
        User user = em.find(User.class, id);
        return user;
    }

    public void delete(User user) {
        if (user == null) {
            throw new NullPointerException("User is Null");
        }
        User user1 = em.find(User.class, user.getId());
        em.remove(user1);
    }

    public void update(User user) {
        if (user == null) {
            throw new NullPointerException("User is Null");
        }
        User userUpd = em.find(User.class, user.getId());
        if (userUpd == null) {
            throw new IllegalArgumentException("this entity does not exist in database");
        }

        userUpd.setBirthDay(user.getBirthDay());
        userUpd.setFirstName(user.getFirstName());
        userUpd.setGender(user.getGender());
        userUpd.setLastName(user.getLastName());
        userUpd.setRecords(user.getRecords());
        userUpd.setWeight(user.getWeight());

        em.persist(userUpd);
    }

    public List<User> findAll() {

        List<User> list;

        list = em.createQuery("SELECT u from User u").getResultList();
        return Collections.unmodifiableList(list);
    }
}
