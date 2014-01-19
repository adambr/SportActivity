package cz.muni.fi.pa165.sportactivitymanager.service.impl;

import cz.muni.fi.pa165.sportactivitymanager.DataAccException;
import cz.muni.fi.pa165.sportactivitymanager.SportActivity;
import cz.muni.fi.pa165.sportactivitymanager.dao.SportActivityDAO;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.changer.SportActivityDTOChanger;
import cz.muni.fi.pa165.sportactivitymanager.service.SportActivityService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Petr Jelínek
 */
@Service
public class SportActivityServiceImpl implements SportActivityService {

    public SportActivityServiceImpl() {
    }

    public SportActivityServiceImpl(SportActivityDAO sportDAO) {
        this.sportDAO = sportDAO;
    }
    private SportActivityDAO sportDAO;

    public SportActivityDAO getSportDAO() {
        return sportDAO;
    }

    public void setSportDAO(SportActivityDAO sportDAO) {
        this.sportDAO = sportDAO;
    }

    @Transactional
    public void create(SportActivityDTO sportActivityDTO) {
        if (sportActivityDTO != null) {
            try {
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        throw new DataAccException("Only role ADMIN can use create method");
                    }
                }
                SportActivity activity = SportActivityDTOChanger.dtoToEntity(sportActivityDTO);
                sportDAO.create(activity);
                sportActivityDTO.setId(activity.getId());
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());

            }
        } else {
            throw new NullPointerException("SportActivity can not be null.");
        }
    }

    @Transactional
    public SportActivityDTO getSportActivity(Long id) {
        SportActivityDTO activityDto = null;

        if (id != null) {
            try {
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        throw new DataAccException("Only role ADMIN can use create method");
                    }
                }
                SportActivity activity = sportDAO.getSportActivity(id);
                activityDto = SportActivityDTOChanger.entityToDTO(activity);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportActivity ID is Null");
        }
        return activityDto;
    }

    @Transactional
    public SportActivityDTO getSportActivity(String name) {
        SportActivityDTO activityDto = null;

        if (name != null) {
            try {
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        throw new DataAccException("Only role ADMIN can use create method");
                    }
                }
                SportActivity activity = sportDAO.getSportActivity(name);
                activityDto = SportActivityDTOChanger.entityToDTO(activity);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportActivity name is Null");
        }
        return activityDto;
    }

    @Transactional
    public void delete(SportActivityDTO sportActivity) {
        if (sportActivity != null) {
            try {
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        throw new DataAccException("Only role ADMIN can use create method");
                    }
                }
                sportDAO.delete(sportActivity.getId());
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportActivity can not be null.");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            try {
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        throw new DataAccException("Only role ADMIN can use create method");
                    }
                }
                sportDAO.delete(id);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("ID can not be null.");
        }
    }

    @Transactional
    public void update(SportActivityDTO sportActivity) {
        if (sportActivity != null) {
            try {
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                        throw new DataAccException("Only role ADMIN can use create method");
                    }
                }
                SportActivity activity = SportActivityDTOChanger.dtoToEntity(sportActivity);
                sportDAO.update(activity);
            } catch (Exception ex) {
                throw new DataAccException(ex.toString());
            }
        } else {
            throw new NullPointerException("SportActivity can not be null.");
        }
    }

    @Transactional
    public List<SportActivityDTO> findAll() {
        List<SportActivityDTO> activityDto = new ArrayList<SportActivityDTO>();

        try {
            List<SportActivity> activity = sportDAO.findAll();
            activityDto = SportActivityDTOChanger.entityListToDTOList(activity);
        } catch (Exception ex) {
            throw new DataAccException(ex.toString());
        }

        return activityDto;
    }
}
