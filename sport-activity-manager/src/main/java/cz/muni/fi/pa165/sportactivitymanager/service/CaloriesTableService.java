
package cz.muni.fi.pa165.sportactivitymanager.service;

import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import java.util.List;

/**
 *
 * @author Michal Galo
 */
public interface CaloriesTableService
{
    /**
     * Creates new CaloriesTableDTO.
     * 
     * @param   caloriesTableDTO   CaloriesTable that we want to store.
     * @throws  NullPointerException when given caloriesTable is NULL.      
     */
    void create(CaloriesTableDTO caloriesTableDTO);
    
    /**
     * Finds existing CaloriesTableDTO by id.
     * 
     * @param   id is ID of CaloriesTable that we want get.
     * @throws  NullPointerException when given id is NULL. 
     * @return  caloriesTable with given id, or Null when CaloriesTable does not exist.
     */
    CaloriesTableDTO get(Long id);   
    
    /**
     * Updates existing CaloriesTableDTO.
     * 
     * @param   caloriesTableDTO     is a CaloriesTable that we want to update.
     * @throws  IlleagalArgumentException when given caloriesTable is null.
     * @throws  NullPointerException when given caloriesTable is null.     
     */
    void update(CaloriesTableDTO caloriesTableDTO);
        
    /**
     * Deletes existing CaloriesTableDTO.
     * 
     * @param   caloriesTable   is a CaloriesTable that we want to delete.
     * @throws  IlleagalArgumentException when given CaloriesTable does not exist.
     * @throws  NullPointerException when given caloriesTable is NULL.     
     */
    void delete(CaloriesTableDTO caloriesTable);
    
    /**
     * Delete existing CaloriesTableDTO by id
     * 
     * @param id    is id of CaloriesTable that we want to delete.
     * @throws NullPointerException when given sportActivity is NULL.
     */
    void delete(Long id);
    
    
    /**
     * Finds all existing CaloriesTableDTO.
     *      
     * @return  List of all CaloriesTable.
     */
    List<CaloriesTableDTO> findAll();    
}
