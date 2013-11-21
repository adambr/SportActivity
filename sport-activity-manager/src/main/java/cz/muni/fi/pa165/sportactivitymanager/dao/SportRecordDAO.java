/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.dao;


import cz.muni.fi.pa165.sportactivitymanager.SportRecord;
import java.util.List;

/**
 *
 * @author Adam Brauner
 */
public interface SportRecordDAO {
     /**
     * Creates new SportRecord
     * 
     * @param sportRecord SportRecord that we store.
     * @throws NullPointerException when argument is Null. 
     * @return nothing.
     */
    void create(SportRecord sportRecord);
    
    /**
     * Find existing SportRecord
     * 
     * @param id is ID of SportRecord that we want get.
     * @throws NullPointerException when ID is NULL. 
     * @return SportRecord with same id, or Null when SportRecord does not exist.
     */
    SportRecord getSportRecord(Long id);
    
    /**
     * Delete existing SportRecord
     * 
     * @param sportRecord is a sportRecord that we want to delete.
     * @throws IlleagalArgumentException when given sportRecord does not exist.
     * @throws NullPointerException when given null.
     * @return nothing
     */
    void delete(SportRecord sportRecord);
    
     /**
     * Delete existing SportRecord
     * 
     * @param id is a id of sportRecord that we want to delete.
     * @throws IlleagalArgumentException when given sportRecord does not exist.
     * @throws NullPointerException when given null.
     * @return nothing
     */
    void delete(Long id);
    
    /**
     * Update existing sportRecord
     * 
     * @param sportRecord is a sportRecord that we want to update.
     * @throws IlleagalArgumentException when given sportRecord does not exist.
     * @throws NullPointerException when given null.
     * @return nothing
     */
    void update(SportRecord sportRecord);
    
    /**
     * Find all existing SportRecord
     * 
     * @param nothing
     * @return List of all SportRecord
     */
    List<SportRecord> findAll();    
    

}
