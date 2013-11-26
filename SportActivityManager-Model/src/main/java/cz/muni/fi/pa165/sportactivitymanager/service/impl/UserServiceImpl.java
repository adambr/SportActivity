/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.service.impl;

import cz.muni.fi.pa165.sportactivitymanager.DataAccException;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.dao.UserDAO;
import cz.muni.fi.pa165.sportactivitymanager.changer.UserDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dobes KUba
 */

@Service
public class UserServiceImpl implements UserService{
    
    private UserDAO uDao;
    
    //renamed name of Method - due to Beans
    public void setuDao(UserDAO uDao) {
        this.uDao = uDao;
    }

    public UserDAO getuDao() {
        return uDao;
    }

    @Transactional
    public void create(UserDTO userDto) {
        if (userDto!=null)
        {
            try{
                    User user = UserDTOChanger.dtoToUserEntity(userDto);
                    uDao.create(user);
                    userDto.setId(user.getId());
            }
            catch(Exception ex){    
                throw new DataAccException(ex.toString());
            }
            //takhle je to zbytečný spíš použít nějakou anotaci, nebo vyhozeni vyjimky primo v XML, neco jako: DataLoader
        }else{
            throw new NullPointerException("User can not be null.");
        }
    }
    
   @Transactional
    public UserDTO getByID(Long id) {
        //if(id<0)
        
        UserDTO userDto = null;
       
        if(id != null){  
            try{
                        User user = uDao.getByID(id);
                        userDto = UserDTOChanger.entityToDTO(user);
                }
                catch(Exception ex){     
                    throw new DataAccException(ex.toString());
                }
        }else{ 
            throw new NullPointerException("User ID is Null");
        }
            return userDto;
    }
   @Transactional
    public void delete(UserDTO userDto){
     if (userDto!=null)
        {
            try{
                    User user = UserDTOChanger.dtoToUserEntity(userDto);
                    uDao.delete(user);
            }
            catch(Exception ex){         
                throw new DataAccException(ex.toString());
            }
        }else{
            throw new NullPointerException("User can not be null.");
        }
    }
   @Transactional
    public void update(UserDTO userDto) {
     if (userDto!=null)
        {
            try{
                    User user = UserDTOChanger.dtoToUserEntity(userDto);
                    uDao.update(user);
            }
            catch(Exception ex){ 
                throw new DataAccException(ex.toString());
            }
        }else{
            throw new NullPointerException("User can not be null.");
        }
    }
   @Transactional
    public List<UserDTO> findAll() {
        List<UserDTO> usersDto = new ArrayList<UserDTO>();
        List<User> users = new ArrayList<User>();
            try{                    
                    users = uDao.findAll();                    
                    usersDto = UserDTOChanger.entityListToDtoList(users);
            }
            catch(Exception ex){
                throw new DataAccException(ex.toString());
                }
            return usersDto;
    }
    
}
