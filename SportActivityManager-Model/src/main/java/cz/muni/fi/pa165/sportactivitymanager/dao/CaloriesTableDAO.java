
package cz.muni.fi.pa165.sportactivitymanager.dao;

import cz.muni.fi.pa165.sportactivitymanager.CaloriesTable;
import java.util.List;

/**
 * 
 * @author Michal Galo
 */
public interface CaloriesTableDAO
{
    /**
     * Creates new CaloriesTable.
     * 
     * @param   caloriesTable   CaloriesTable that we want to store.
     * @throws  NullPointerException when given caloriesTable is NULL.      
     */
    void create(CaloriesTable caloriesTable);
    
    /**
     * Finds existing CaloriesTable by id.
     * 
     * @param   id is ID of CaloriesTable that we want get.
     * @throws  NullPointerException when given id is NULL. 
     * @return  caloriesTable with given id, or Null when CaloriesTable does not exist.
     */
    CaloriesTable get(Long id);   
    
    /**
     * Updates existing CaloriesTable.
     * 
     * @param   caloriesTable     is a CaloriesTable that we want to update.
     * @throws  IlleagalArgumentException when given caloriesTable is null.
     * @throws  NullPointerException when given caloriesTable is null.     
     */
    void update(CaloriesTable caloriesTable);
    
    /**
     * Delete existing CaloriesTable by id.
     * 
     * @param   id  is id of CaloriesTable that we want to delete.
     * @throws  IlleagalArgumentException when CaloriesTable with given id does not exist.
     * @throws  NullPointerException when given id is NULL.     
     */
    void delete(Long id);
    
    /**
     * Deletes existing CaloriesTable.
     * 
     * @param   caloriesTable   is a CaloriesTable that we want to delete.
     * @throws  IlleagalArgumentException when given CaloriesTable does not exist.
     * @throws  NullPointerException when given caloriesTable is NULL.     
     */
    void delete(CaloriesTable caloriesTable);      
    
    /**
     * Finds all existing CaloriesTable.
     *      
     * @return  List of all CaloriesTable.
     */
    List<CaloriesTable> findAll();    
    
}
