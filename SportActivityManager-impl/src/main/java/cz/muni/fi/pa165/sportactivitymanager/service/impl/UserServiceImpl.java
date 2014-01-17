package cz.muni.fi.pa165.sportactivitymanager.service.impl;

import cz.muni.fi.pa165.sportactivitymanager.DataAccException;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.dao.UserDAO;
import cz.muni.fi.pa165.sportactivitymanager.changer.UserDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dobes Kuba
 */
//TODO  security  methods
@Service
public class UserServiceImpl implements UserService {

    private UserDAO uDao;

    public void setuDao(UserDAO uDao) {
        this.uDao = uDao;
    }

    public UserDAO getuDao() {
        return uDao;
    }

    @Transactional
    public void create(UserDTO userDto) {
        if (userDto != null) {
            try {
                //tato metoda create(), je použita ve webovém rozhraní pro vytvoření nového usera. Proto se musí zabezpečit i zde.
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    //vypíše info o tom která trida autentizovala, jaký user, jaké oprávnění...
                    System.out.println("SECURITY CONTEXT HOLDER: " + SecurityContextHolder.getContext().getAuthentication());
                    //role která je predaná v contextu se pridá do authorities
                    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                    authorities.addAll((List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities());

                    //pokud v authorities není role user tak se neprovede create a vyhodí to vyjímku.
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        //System.out.println("SEC CX isA");
                        throw new DataAccException("Only role ADMIN can use create method");
                    }
                }

                User user = UserDTOChanger.dtoToUserEntity(userDto);
                uDao.create(user);
                userDto.setId(user.getId());
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("User can not be null.");
        }
    }

    @Transactional
    public UserDTO getByID(Long id) {

        UserDTO userDto = null;

        if (id != null) {
            try {
                User user = uDao.getByID(id);
                userDto = UserDTOChanger.entityToDTO(user);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("User ID is Null");
        }
        return userDto;
    }

    @Transactional
    public void delete(UserDTO userDto) {
        if (userDto != null) {
            try {
                User user = UserDTOChanger.dtoToUserEntity(userDto);
                uDao.delete(user);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("User can not be null.");
        }
    }

    @Transactional
    public void update(UserDTO userDto) {
        if (userDto != null) {
            try {
                User user = UserDTOChanger.dtoToUserEntity(userDto);
                uDao.update(user);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("User can not be null.");
        }
    }

    @Transactional
    public List<UserDTO> findAll() {
        List<UserDTO> usersDto = new ArrayList<UserDTO>();
        List<User> users;
        try {
            users = uDao.findAll();
            usersDto = UserDTOChanger.entityListToDtoList(users);
        } catch (Exception ex) {
            throw new DataAccException(ex.toString());
        }
        return usersDto;
    }
}
