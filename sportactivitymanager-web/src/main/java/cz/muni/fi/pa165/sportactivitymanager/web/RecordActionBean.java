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
import cz.muni.fi.pa165.sportactivitymanager.web.tools.AuthTool;
import java.util.Collection;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Adam Brauner
 */
@UrlBinding("/records/{$event}/record/{record.id}/user/{user.id}")
public class RecordActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);
    @SpringBean
    protected SportRecordService srs;

    Authentication auth;

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

    @Before
    public Resolution authorization() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (AuthTool.isRole(auth, ADMIN)) {
            authRole = ADMIN;
        } else if (AuthTool.isRole(auth, USER)) {
            authRole = USER;
        } else {
            return new RedirectResolution(IndexActionBean.class);
        }

        authName = auth.getName();
        return null;
    }

    @DefaultHandler
    public Resolution list() {

        if (authRole.equals(ADMIN)) {
            String ids = getContext().getRequest().getParameter("user.id");
            user = userService.getByID(Long.parseLong(ids));
        } else {
            user = userService.getByLogin(authName);
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

        if (authRole.equals(ADMIN)) {
            return new RedirectResolution(this.getClass(), "list")
                    .addParameter("user.id", user.getId());
        } else {
            return new RedirectResolution(this.getClass());
        }
    }

    public Resolution edit() {
        String ids = getContext().getRequest().getParameter("record.id");
        Long id;

        try {
            id = Long.parseLong(ids);
        } catch (NumberFormatException ex) {
            return new RedirectResolution(this.getClass());
        }

        if (authRole.equals(ADMIN)) {
            record = srs.getSportRecord(id);
        } else {
            record = srs.getSportRecord(id);
            user = userService.getByLogin(authName);
            if (!user.getRecords().contains(record)) {
                return new RedirectResolution(this.getClass());
            }
            record = srs.getSportRecord(id);
        }

        activity = activityService.findAll();
        return new ForwardResolution("/record/edit.jsp");
    }

    public Resolution save() {

        if (authRole.equals(ADMIN)) {
            user = userService.getByID(user.getId());
        } else {
            user = userService.getByLogin(authName);
            if (!user.getRecords().contains(record)) {
                return new RedirectResolution(this.getClass());
            }
        }

        record.setActivityDTO(activityService.getSportActivity(aktivita));

        srs.update(record);

        if (authRole.equals(ADMIN)) {
            return new RedirectResolution(this.getClass(), "list")
                    .addParameter("user.id", user.getId());
        } else {
            return new RedirectResolution(this.getClass());
        }
    }

    public Resolution delete() {

        if (authRole.equals(ADMIN)) {
            String ids = getContext().getRequest().getParameter("user.id");
            user = userService.getByID(Long.parseLong(ids));
        } else {
            user = userService.getByLogin(authName);
            if (!user.getRecords().contains(record)) {
                return new RedirectResolution(this.getClass());
            }
        }

        record = srs.getSportRecord(record.getId());

        srs.delete(record.getId());

        if (authRole.equals(ADMIN)) {
            return new RedirectResolution(this.getClass(), "list")
                    .addParameter("user.id", user.getId());
        } else {
            return new RedirectResolution(this.getClass());
        }
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
