/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.dto;

import cz.muni.fi.pa165.sportactivitymanager.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dobes KUba
 */

public class UserDTOChanger {
  
    //convert userDTO to Entity User
    public static User dtoToUserEntity(UserDTO userDto) {
        
        if(null == userDto) return null;
        
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDay(userDto.getBirthDay());
        user.setGender(userDto.getGender());
        user.setWeight(userDto.getWeight());
        return user;
    }

    //convert user to userDTO
    public static UserDTO entityToDTO(User user) {
        if(null == user) return null;
        
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthDay(user.getBirthDay());
        userDto.setGender(user.getGender());
        userDto.setWeight(user.getWeight());
        return userDto;
    }
  
    public static List<UserDTO> entityListToDtoList( List<User> users ) {
        if(users == null) return null;
        
        List<UserDTO> userDtoList =  new ArrayList<UserDTO>();
        for(int i = 0; i<users.size(); i++){
            userDtoList.add(UserDTOChanger.entityToDTO(users.get(i)));
        }
        return userDtoList;
    }

}
