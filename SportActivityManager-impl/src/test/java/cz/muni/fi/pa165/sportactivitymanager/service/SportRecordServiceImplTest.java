package cz.muni.fi.pa165.sportactivitymanager.service;

import cz.muni.fi.pa165.sportactivitymanager.dao.SportRecordDAO;
import cz.muni.fi.pa165.sportactivitymanager.changer.SportRecordDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.impl.SportRecordServiceImpl;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Adam Brauner
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext.xml"})
public class SportRecordServiceImplTest {

    @Autowired
    private SportRecordService sportService;
    private SportRecordServiceImpl mockService;
    private SportRecordDAO mockDAO;

    @Before
    public void setUp() {
        mockDAO = mock(SportRecordDAO.class);
        mockService = new SportRecordServiceImpl();
        mockService.setSRDao(mockDAO);
    }

    @Test
    public void testCreate() {
        SportRecordDTO sportDto = new SportRecordDTO();
        sportDto.setDistance(100);
        sportDto.setDuration(Long.MIN_VALUE);
        sportDto.setStartTime(new Date());

        mockService.create(sportDto);

        verify(mockDAO)
                .create(SportRecordDTOChanger.DTOToEntity(sportDto));
    }

    @Test
    public void testFindAll() {
        List<SportRecordDTO> listRecordDto = mockService.findAll();
        verify(mockDAO).findAll();
    }

    @Test
    public void testUpdate() {
        SportRecordDTO sportDto = new SportRecordDTO();
        sportDto.setDistance(100);
        sportDto.setDuration(Long.MIN_VALUE);
        sportDto.setStartTime(new Date());

        mockService.update(sportDto);
        verify(mockDAO)
                .update(SportRecordDTOChanger.DTOToEntity(sportDto));
    }

    @Test
    public void testGetById() {
        mockService.getSportRecord(1L);
        verify(mockDAO)
                .getSportRecord(1L);
    }
}
