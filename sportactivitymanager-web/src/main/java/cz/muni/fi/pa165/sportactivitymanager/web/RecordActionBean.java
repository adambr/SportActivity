/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.web;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportActivityService;
import cz.muni.fi.pa165.sportactivitymanager.service.SportRecordService;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Adam Brauner
 */
@UrlBinding("/records/{$event}/user/{user.id}/")
public class RecordActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);
    @SpringBean
    protected SportRecordService srs;
    
    
    private List<SportRecordDTO> records;
    @Validate(on = {"add", "save"}, required = true)
    private Long aktivita;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "duration", required = true),
        @Validate(on = {"add", "save"}, field = "distance", required = true),
        @Validate(on = {"add", "save"}, field = "startTime", required = true)
    })
    private SportRecordDTO record;

    public SportRecordDTO getRecord() {
        return record;
    }

    public void setRecord(SportRecordDTO record) {
        this.record = record;
    }

    public Long getAktivita() {
        return aktivita;
    }

    public void setAktivita(Long aktivita) {
        this.aktivita = aktivita;
    }

    public List<SportRecordDTO> getRecords() {
        return records;
    }
    @SpringBean
    protected SportActivityService activityService;
    private Collection<SportActivityDTO> activity;

    public Collection<SportActivityDTO> getActivity() {
        return activity;
    }
    @SpringBean
    protected UserService userService;
    //pro zobrazení seznamu uživatelů
    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @DefaultHandler
    public Resolution list() {
        
        
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> roles;
        roles = (List<GrantedAuthority>) auth.getAuthorities();


         if (!roles.contains(new SimpleGrantedAuthority("ADMIN"))) {
             user = userService.getByLogin(auth.getName());
         } else {
             String ids = getContext().getRequest().getParameter("user.id");
             user = userService.getByID(Long.parseLong(ids));
         }

        record = new SportRecordDTO();
        records = user.getRecords();
        activity = activityService.findAll();
        return new ForwardResolution("/record/list.jsp");
    }

    public Resolution add() {
        user = userService.getByID(user.getId());        
        record.setActivityDTO(activityService.getSportActivity(aktivita));
        user.getRecords().add(record);
        srs.create(record);
        userService.update(user);

        return new RedirectResolution(this.getClass(), "list")
                .addParameter("user.id", user.getId());
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit"})
    public void loadRecordFromDatabase() {
            String ids = getContext().getRequest().getParameter("record.id");
        if (ids == null) {
            return;
        }
        record = srs.getSportRecord(Long.parseLong(ids));
    }

    public Resolution edit() {
        activity = activityService.findAll();
        return new ForwardResolution("/record/edit.jsp"); 
    }   

    public Resolution save() {
        log.info(record.getId()+"  "+ record.getDuration()+"  "+ record.getDistance());
        user = userService.getByID(user.getId()); 
        record.setActivityDTO(activityService.getSportActivity(aktivita));
        srs.update(record);
        return new RedirectResolution(this.getClass(), "list")
                .addParameter("user.id", user.getId());
    }
    
    
    public Resolution delete() {
        String ids = getContext().getRequest().getParameter("user.id");
        user = userService.getByID(Long.parseLong(ids));
        log.info(record.getId().toString());
        record = srs.getSportRecord(record.getId());
        srs.delete(record.getId());
        return new RedirectResolution(this.getClass(), "list")
                .addParameter("user.id", user.getId());
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        String ids = getContext().getRequest().getParameter("user.id");
        user = userService.getByID(Long.parseLong(ids));
        records = srs.findAll();
        activity = activityService.findAll();
        return null;
    }
}
