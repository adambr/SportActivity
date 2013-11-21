/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.dao;

import cz.muni.fi.pa165.sportactivitymanager.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dobes Kuba
 */
public interface UserDAO {
    /**
     * Creates new User
     * 
     * @param user User that we store.
     * @throws IllegalArgumentException when argument is not instance of User class. 
     * @return nothing.
     */
    void create(User user);
    
    /**
     * Find existing User
     * 
     * @param id is ID of User that we want getByID.
     * @throws IllegalArgumentException when ID is NULL. 
     * @return User with same id, or Null when user does not exist.
     */
    User getByID(Long id);
    
    /**
     * Delete existing User
     * 
     * @param user is a user that we want to delete.
     * @throws IlleagalArgumentException when given user does not exist.
     * @throws NullPointerException when given user is not in DB.
     * @return nothing
     */
    void delete(User user);
    
    /**
     * Update existing User
     * 
     * @param user is a user that we want to update.
     * @throws IlleagalArgumentException when given user does not exist.
     * @throws NullPointerException when given user is not in DB.
     * @return nothing
     */
    void update(User user);
    
    /**
     * Find all existing Users
     * 
     * @param nothing
     * @return List of all Users
     */
    List<User> findAll();
   
}
