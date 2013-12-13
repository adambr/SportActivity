package cz.muni.fi.pa165.sportactivitymanager.service;

import cz.muni.fi.pa165.sportactivitymanager.dao.SportActivityDAO;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.changer.SportActivityDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.service.impl.SportActivityServiceImpl;
import java.util.List;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.Test;

/**
 *
 * @author Petr Jelínek
 */
public class SportActivityServiceImplTest {

    private SportActivityService mockService;
    private SportActivityDAO mockDAO;

    @Before
    public void setUp() {
        mockDAO = mock(SportActivityDAO.class);
        mockService = new SportActivityServiceImpl(mockDAO);
    }

    @Test
    public void testCreate() {
        SportActivityDTO sportDto = new SportActivityDTO();
        sportDto.setName("diving");

        mockService.create(sportDto);

        verify(mockDAO)
                .create(SportActivityDTOChanger.dtoToEntity(sportDto));
    }

    @Test
    public void testFindAll() {
        List<SportActivityDTO> listActivityDto = mockService.findAll();
        verify(mockDAO).findAll();
    }

    @Test
    public void testUpdate() {
        SportActivityDTO sportDto = new SportActivityDTO();
        sportDto.setName("diving");

        mockService.update(sportDto);
        verify(mockDAO)
                .update(SportActivityDTOChanger.dtoToEntity(sportDto));
    }

    @Test
    public void testDelete() {
        SportActivityDTO sportDto = new SportActivityDTO();
        sportDto.setId(2L);
        sportDto.setName("diving");

        mockService.delete(sportDto.getId());
        verify(mockDAO)
                .delete(sportDto.getId());
    }

    @Test
    public void testGetByName() {
        mockService.getSportActivity("diving");
        verify(mockDAO)
                .getSportActivity("diving");
    }

    @Test
    public void testGetById() {
        mockService.getSportActivity(1L);
        verify(mockDAO)
                .getSportActivity(1L);
    }
}
