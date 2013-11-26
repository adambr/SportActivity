package cz.muni.fi.pa165.sportactivitymanager.dependencyInjection;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportActivityService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Petr Jelínek
 */
public class SportActivitySpringInjectionTest extends BaseSpringInjectionTest {
    
    @Autowired
    private SportActivityService sportService;
    
    @Test
    public void testCreateAndFind() {
        SportActivityDTO sportDto = new SportActivityDTO();
        sportDto.setName("diving");

        sportService.create(sportDto);
        assertNotNull(sportDto.getId());

        SportActivityDTO sport2fromDB = sportService.getSportActivity(sportDto.getName());
        assertEquals(sportDto, sport2fromDB);
    }
    
}
