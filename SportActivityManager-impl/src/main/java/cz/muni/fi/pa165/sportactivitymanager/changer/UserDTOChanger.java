package cz.muni.fi.pa165.sportactivitymanager.changer;

import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dobes Kuba
 */
public class UserDTOChanger {

    public static User dtoToUserEntity(UserDTO userDto) {

        if (null == userDto) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDay(userDto.getBirthDay());
        user.setGender(userDto.getGender());
        user.setWeight(userDto.getWeight());
        user.setRecords(SportRecordDTOChanger.DTOListToEntityList(userDto.getRecords()));
        user.setPassword(userDto.getPassword());
        user.setLogin(userDto.getLogin());
        user.setCredentials(userDto.getCredentials());
        return user;
    }

    public static UserDTO entityToDTO(User user) {
        if (null == user) {
            return null;
        }

        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthDay(user.getBirthDay());
        userDto.setGender(user.getGender());
        userDto.setWeight(user.getWeight());
        userDto.setRecords(SportRecordDTOChanger.entityListToDTOList(user.getRecords()));
        userDto.setPassword(user.getPassword());
        userDto.setLogin(user.getLogin());
        userDto.setCredentials(user.getCredentials());
        return userDto;
    }

    public static List<UserDTO> entityListToDtoList(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserDTO> userDtoList = new ArrayList<UserDTO>();
        for (int i = 0; i < users.size(); i++) {
            userDtoList.add(UserDTOChanger.entityToDTO(users.get(i)));
        }
        return userDtoList;
    }
}
