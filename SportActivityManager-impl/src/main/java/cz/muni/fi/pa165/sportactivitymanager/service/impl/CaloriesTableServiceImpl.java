
package cz.muni.fi.pa165.sportactivitymanager.service.impl;

import cz.muni.fi.pa165.sportactivitymanager.CaloriesTable;
import cz.muni.fi.pa165.sportactivitymanager.DataAccException;
import cz.muni.fi.pa165.sportactivitymanager.dao.CaloriesTableDAO;
import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.changer.CaloriesTableDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.service.CaloriesTableService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Galo
 */
@Service
public class CaloriesTableServiceImpl implements CaloriesTableService
{
    private CaloriesTableDAO caloriesTableDAO;
    
    public CaloriesTableServiceImpl() {}
    
    public CaloriesTableServiceImpl(CaloriesTableDAO caloriesTableDAO)
    {
        this.caloriesTableDAO = caloriesTableDAO;
    }
    
    public CaloriesTableDAO getDAO()                      { return caloriesTableDAO; }    
    public void setDAO(CaloriesTableDAO caloriesTableDAO) { this.caloriesTableDAO = caloriesTableDAO; }
       
    @Transactional
    public void create(CaloriesTableDTO caloriesTableDTO)
    {
        if (caloriesTableDTO == null) throw new NullPointerException("CaloriesTable is null.");
        
        try 
        {
            CaloriesTable caloriesTable = CaloriesTableDTOChanger.dtoToEntity(caloriesTableDTO);
            caloriesTableDAO.create(caloriesTable);
            caloriesTableDTO.setId(caloriesTable.getId());
        } 
        catch (Exception ex) 
        {
            throw new DataAccException(ex.toString());
        }
    }    
    
    @Transactional
    public CaloriesTableDTO get(Long id)
    {
        if (id == null) throw new NullPointerException("ID is null.");
        
        CaloriesTableDTO caloriesTableDTO = null;
        
        try 
        {
            CaloriesTable caloriesTable = caloriesTableDAO.get(id);
            caloriesTableDTO = CaloriesTableDTOChanger.entityToDTO(caloriesTable);
        } 
        catch (Exception ex) 
        {
            throw new DataAccException(ex.toString());
        }
        
        return caloriesTableDTO;        
    }      
    
    @Transactional
    public void update(CaloriesTableDTO caloriesTableDTO)
    {
        if (caloriesTableDTO == null) throw new NullPointerException("CaloriesTable is null.");
        
        try 
        {
            CaloriesTable caloriesTable = CaloriesTableDTOChanger.dtoToEntity(caloriesTableDTO);
            caloriesTableDAO.update(caloriesTable);
        } 
        catch (Exception ex) 
        {
            throw new DataAccException(ex.toString());
        }        
    }    
        
    @Transactional
    public void delete(CaloriesTableDTO caloriesTableDTO)
    {
        if (caloriesTableDTO == null) throw new NullPointerException("CaloriesTable is null.");
        
        try 
        {
            CaloriesTable caloriesTable = CaloriesTableDTOChanger.dtoToEntity(caloriesTableDTO);
            caloriesTableDAO.delete(caloriesTable);
        } 
        catch (Exception ex) 
        {
            throw new DataAccException(ex.toString());
        }
    }         
    
    @Transactional
    public void delete(Long id) 
    {
        if (id != null) throw new NullPointerException("ID is null.");
        
        try 
        {
            caloriesTableDAO.delete(id);
        } 
        catch (Exception ex) 
        {
            throw new DataAccException(ex.toString());
        }
    }
    
    @Transactional
    public List<CaloriesTableDTO> findAll()
    {
        List<CaloriesTableDTO> caloriesTableDTOList = new ArrayList<CaloriesTableDTO>();         
        
        try
        {
            List<CaloriesTable> caloriesTableList = caloriesTableDAO.findAll();
            caloriesTableDTOList = CaloriesTableDTOChanger.entityListToDTOList(caloriesTableList);
        } 
        catch (Exception ex) 
        {
            throw new DataAccException(ex.toString());
        }        
        
        return caloriesTableDTOList;        
    }        
}
