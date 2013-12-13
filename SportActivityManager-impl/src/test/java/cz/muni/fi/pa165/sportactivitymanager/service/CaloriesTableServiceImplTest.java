package cz.muni.fi.pa165.sportactivitymanager.service;

import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dao.CaloriesTableDAO;
import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.changer.CaloriesTableDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.service.impl.CaloriesTableServiceImpl;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Michal Galo
 */
public class CaloriesTableServiceImplTest {

    private CaloriesTableService mockService;
    private CaloriesTableDAO mockDAO;

    @Before
    public void setUp() {
        mockDAO = mock(CaloriesTableDAO.class);
        mockService = new CaloriesTableServiceImpl(mockDAO);
    }

    @Test
    public void testCreate() {
        CaloriesTableDTO tableDTO = new CaloriesTableDTO();
        tableDTO.setCalories60Kg(333);
        tableDTO.setCalories70Kg(444);
        tableDTO.setCalories80Kg(555);
        tableDTO.setCalories90Kg(666);
        tableDTO.setGender(Gender.MALE);

        mockService.create(tableDTO);

        verify(mockDAO).create(CaloriesTableDTOChanger.dtoToEntity(tableDTO));
    }

    @Test
    public void testUpdate() {
        CaloriesTableDTO tableDTO = new CaloriesTableDTO();
        tableDTO.setCalories60Kg(123);
        tableDTO.setCalories70Kg(456);
        tableDTO.setCalories80Kg(789);
        tableDTO.setCalories90Kg(999);
        tableDTO.setGender(Gender.MALE);

        mockService.update(tableDTO);

        verify(mockDAO).update(CaloriesTableDTOChanger.dtoToEntity(tableDTO));
    }

    @Test
    public void testDelete() {
        CaloriesTableDTO tableDTO = new CaloriesTableDTO();
        tableDTO.setCalories60Kg(147);
        tableDTO.setCalories70Kg(258);
        tableDTO.setCalories80Kg(369);
        tableDTO.setCalories90Kg(852);
        tableDTO.setGender(Gender.MALE);

        mockService.delete(tableDTO.getId());

        verify(mockDAO).delete(tableDTO.getId());
    }

    @Test
    public void testFindAll() {
        List<CaloriesTableDTO> tableDTOList = mockService.findAll();
        verify(mockDAO).findAll();
    }
}
