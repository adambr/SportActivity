/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.dao;

import cz.muni.fi.pa165.sportactivitymanager.SportActivity;
import java.util.List;

/**
 *
 * @author Petr Jelínek
 */
public interface SportActivityDAO {
    
    /**
     * Creates new SportActivity
     * 
     * @param sportActivity SportActivity that we store.
     * @throws NullPointerException when given sportActivity is NULL. 
     * @return nothing.
     */
    void create(SportActivity sportActivity);
    
    /**
     * Find existing SportActivity by id
     * 
     * @param id is ID of SportActivity that we want get.
     * @throws NullPointerException when ID is NULL. 
     * @return SportActivity with same id, or Null when SportActivity does not exist.
     */
    SportActivity getSportActivity(Long id);
    
    /**
     * Find existing SportActivity by name
     * 
     * @param name is name of SportActivity that we want get.
     * @throws IllegalArgumentException when name is empty 
     * @throws NullPointerException when name is NULL. 
     * @return SportActivity with same name, or Null when SportActivity does not exist.
     */
    SportActivity getSportActivity(String name);
    
    /**
     * Delete existing SportActivity
     * 
     * @param sportActivity is a SportActivity that we want to delete.
     * @throws IlleagalArgumentException when given SportActivity does not exist.
     * @throws NullPointerException when given sportActivity is NULL.
     * @return nothing
     */
    void delete(SportActivity sportActivity);
    
    /**
     * Delete existing SportActivity by Id
     * 
     * @param id is id of SportActivity that we want to delete.
     * @throws IlleagalArgumentException when SportActivity with given id does not exist.
     * @throws NullPointerException when given id is NULL.
     * @return nothing
     */
    void delete(Long id);
    
    /**
     * Update existing sportActivity
     * 
     * @param sportActivity is a SportActivity that we want to update.
     * @throws IlleagalArgumentException when given sportActivity does not exist.
     * @throws NullPointerException when given sportActivity is NULL.
     * @return nothing
     */
    void update(SportActivity sportActivity);
    
    /**
     * Find all existing SportActivity
     * 
     * @param nothing
     * @return List of all SportActivity
     */
    List<SportActivity> findAll();
}
