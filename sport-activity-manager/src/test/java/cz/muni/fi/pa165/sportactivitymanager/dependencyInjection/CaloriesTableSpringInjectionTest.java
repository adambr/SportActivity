
package cz.muni.fi.pa165.sportactivitymanager.dependencyInjection;

import cz.muni.fi.pa165.sportactivitymanager.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.CaloriesTableService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Michal Galo
 */
public class CaloriesTableSpringInjectionTest extends BaseSpringInjectionTest
{
    @Autowired
    private CaloriesTableService tableService;
    
    @Test
    public void testCreateAndFind()
    {
        CaloriesTableDTO tableDTO = new CaloriesTableDTO();
        tableDTO.setCalories60Kg(100);
        tableDTO.setCalories70Kg(150);
        tableDTO.setCalories80Kg(200);
        tableDTO.setCalories90Kg(250);
        tableDTO.setGender(Gender.MALE);
       
        tableService.create(tableDTO);
        assertNotNull(tableDTO.getId());
       
        CaloriesTableDTO tableDB = tableService.get(tableDTO.getId());
        assertEquals(tableDTO, tableDB);
    }    
}
