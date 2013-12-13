package cz.muni.fi.pa165.sportactivitymanager.service;

import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.changer.UserDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.dao.UserDAO;
import cz.muni.fi.pa165.sportactivitymanager.dao.impl.UserDAOImpl;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;


import cz.muni.fi.pa165.sportactivitymanager.service.impl.UserServiceImpl;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dobes Kuba
 */
public class UserServiceImplTest {

    private UserServiceImpl uService;
    private UserDAO userMockDAO;
    private UserDTO userDto;
    private User user;
    private User user2;

    @Before
    public void setUp() {
        List<User> users = new ArrayList<User>();


        uService = new UserServiceImpl();

        userMockDAO = mock(UserDAOImpl.class);
        uService.setuDao(userMockDAO);

        user = new User();
        Date birthD3 = new Date(100, 10, 20);
        user.setBirthDay(birthD3);
        user.setFirstName("Matin");
        user.setLastName("Hajanek");
        user.setGender(Gender.MALE);
        user.setWeight(57);
        user.setId(Long.valueOf(11));

        user2 = new User();
        Date birthD5 = new Date(100, 10, 20);
        user2.setBirthDay(birthD3);
        user2.setFirstName("Matin");
        user2.setLastName("Hajanek");
        user2.setGender(Gender.MALE);
        user2.setWeight(57);
        user2.setId(Long.valueOf(22));


        users.add(user);
        users.add(user2);

        when(userMockDAO.findAll()).thenReturn(users);
        when(userMockDAO.getByID(Long.valueOf(22))).thenReturn(user2);
    }

    @Test
    public void testCreate() {

        uService.create(UserDTOChanger.entityToDTO(user));
        verify(userMockDAO).create(user);
    }

    @Test
    public void testCreateNullUser() {
        UserDTO userDto = null;

        try {
            uService.create(userDto);
            fail("Create was called with null UserDto");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testGet() {

        UserDTO userDto = uService.getByID(Long.valueOf(22));
        verify(userMockDAO).getByID(Long.valueOf(22));

        assertEquals(userDto.getId(), user2.getId());
        AssertUserCompletely(userDto, UserDTOChanger.entityToDTO(user2));
    }

    @Test
    public void testFindAll() {

        List<UserDTO> listUsersDto = new ArrayList<UserDTO>();

        listUsersDto = uService.findAll();
        verify(userMockDAO).findAll();

        assertEquals(2, listUsersDto.size());
    }

    @Test
    public void testUpdate() {
        uService.update(UserDTOChanger.entityToDTO(user));
        verify(userMockDAO).update(user);
    }

    @Test
    public void TestDelete() {

        uService.delete(UserDTOChanger.entityToDTO(user));
        verify(userMockDAO).delete(user);

    }

    private void AssertUserCompletely(UserDTO userDto1, UserDTO userDto2) {
        assertEquals(userDto1.getBirthDay(), userDto2.getBirthDay());
        assertEquals(userDto1.getGender(), userDto2.getGender());
        assertEquals(userDto1.getId(), userDto2.getId());
        assertEquals(userDto1.getLastName(), userDto2.getLastName());
        assertEquals(userDto1.getWeight(), userDto2.getWeight());
    }
}
