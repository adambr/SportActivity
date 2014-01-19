package cz.muni.fi.pa165.sportactivitymanager.service.impl;

import cz.muni.fi.pa165.sportactivitymanager.DataAccException;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.dao.UserDAO;
import cz.muni.fi.pa165.sportactivitymanager.changer.UserDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
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
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
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
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        throw new DataAccException("Only role ADMIN can use delete method");
                    }
                }
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
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {

                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
                    UserDTO fromDB = UserDTOChanger.entityToDTO(uDao.getByLogin(userLogin));

                    if (!userDto.equals(fromDB)) {
                        log.info("user is not owner");
                        if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                            log.info("not admin");
                            throw new DataAccException("Only owner or admin can update user.");
                        }
                    }
                }
                log.info("test passed, lets update user");
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

    @Transactional
    public UserDTO getByLogin(String login) {

        UserDTO userDto = null;

        if (login != null) {
            try {
                User user = uDao.getByLogin(login);
                userDto = UserDTOChanger.entityToDTO(user);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("User login is Null");
        }
        return userDto;
    }
}
