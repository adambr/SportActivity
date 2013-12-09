/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.dependencyInjection;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportRecordService;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phoenix-PC
 */
public class SportRecordSpringInjectionTest extends BaseSpringInjectionTest{
    @Autowired
    private SportRecordService sportService;
    
    @Test
    public void testCreateAndFind() {
        SportRecordDTO sportDto = new SportRecordDTO();
        sportDto.setDistance(100);
        sportDto.setDuration(Long.MIN_VALUE);
        sportDto.setStartTime(new Date());
        
        sportService.create(sportDto);
        assertNotNull(sportDto.getId());

        SportRecordDTO sport2fromDB = sportService.getSportRecord(sportDto.getId());
        assertEquals(sportDto, sport2fromDB);
    }    
}
