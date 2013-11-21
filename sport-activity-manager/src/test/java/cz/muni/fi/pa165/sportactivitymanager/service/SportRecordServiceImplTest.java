/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.service;

import cz.muni.fi.pa165.sportactivitymanager.dao.SportRecordDAO;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportRecordService;
import cz.muni.fi.pa165.sportactivitymanager.service.impl.SportRecordServiceImpl;
import cz.muni.fi.pa165.sportactivitymanager.service.impl.SportRecordServiceImpl;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Phaser
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
        // service ve ktere se pouziva mock DAO misto zive implementace
        mockDAO = mock(SportRecordDAO.class);
        mockService = new SportRecordServiceImpl();
        mockService.setSRDao(mockDAO);
    }

    @Test //mock DAO test
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
