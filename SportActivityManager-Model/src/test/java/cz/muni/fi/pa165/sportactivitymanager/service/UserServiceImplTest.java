/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
  //  private UserDAOMock userMockDAO;
    private UserDAO userMockDAO;
    
    private UserDTO userDto;
    private User user;
    private User user2;
    
    //creates instance of userServiceImpl and sets it's DAO to MockDAO
    @Before
    public void setUp() {
        List<User> users = new ArrayList<User>();
     //   List<User> users = new ArrayList<>();
          
        uService = new UserServiceImpl();
      //  userMockDAO = new UserDAOMock();
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
    /*
     * Try create new userDto 
     * 
     */
    @Test
    public void testCreate(){
   
        uService.create(UserDTOChanger.entityToDTO(user));
        verify(userMockDAO).create(user);
    }
    
     /*
     * Try create new Null user
     */
    @Test
    public void testCreateNullUser(){
        UserDTO userDto = null;
        
        try{ 
            uService.create(userDto);
            fail("Create was called with null UserDto");
       }catch(NullPointerException ex){}
    }
    
    /**
     * Test of Get method, of UserService class
     *
     * Get userDTOs with ID 22.
     * Then assertEquals tests, whether ID of get IDs is same.  
     * AssertUserCompletely tests whether attributes of 2 objects are same. 
     */    
    @Test
    public void testGet(){
        
        UserDTO userDto = uService.getByID(Long.valueOf(22));
        verify(userMockDAO).getByID(Long.valueOf(22));
        
        assertEquals(userDto.getId(), user2.getId());
        AssertUserCompletely(userDto,UserDTOChanger.entityToDTO(user2));
    }
    /**
     * Test of findAll method, of User class
     *
     * Create 3 new users.
     * Then tests findAll method, that should return all 2 object into List
     */
    @Test
    public void testFindAll() {
        
        List<UserDTO> listUsersDto = new ArrayList<UserDTO>();
                
        listUsersDto = uService.findAll();
        verify(userMockDAO).findAll();
        
        assertEquals(2, listUsersDto.size());
 }
    
    /**
     * Test of Update method, that should update information about userDto1
     */
    @Test
    public void testUpdate()
    {
        
     //   List<UserDTO> listUsersDto = new ArrayList<UserDTO>();
                
        uService.update(UserDTOChanger.entityToDTO(user));
        verify(userMockDAO).update(user);
    }
    
    /**
     * Test of Delete method, that should remove userDto1 
     */
    @Test
    public void TestDelete(){
        
        uService.delete(UserDTOChanger.entityToDTO(user));
        verify(userMockDAO).delete(user);
               
    }
    
    /**
     * Method for comparison information of 2 userDtos that should be same
     * throw Error when aren't same
     */
    private void AssertUserCompletely(UserDTO userDto1, UserDTO userDto2) {
        assertEquals(userDto1.getBirthDay(),userDto2.getBirthDay());
        assertEquals(userDto1.getGender(), userDto2.getGender());
        assertEquals(userDto1.getId(), userDto2.getId());
        assertEquals(userDto1.getLastName(), userDto2.getLastName());
        assertEquals(userDto1.getWeight(), userDto2.getWeight());
    }
}
