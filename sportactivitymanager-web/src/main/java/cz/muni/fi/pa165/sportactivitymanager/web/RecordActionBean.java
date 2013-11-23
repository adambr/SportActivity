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
import java.util.Collection;
import java.util.List;
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

/**
 *
 * @author Adam Brauner
 */
@UrlBinding("/records/{$event}/{record.id}/")
public class RecordActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);
    @SpringBean
    protected SportRecordService srs;
    private List<SportRecordDTO> records;
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
        record = new SportRecordDTO();
        String ids = getContext().getRequest().getParameter("user.id");
        log.info("******************************************");
        log.info(ids);
        log.info("******************************************");
        user = userService.getByID(Long.parseLong(ids));
        records = srs.findAll();
        activity = activityService.findAll();
        return new ForwardResolution("/record/list.jsp");
    }

    public Resolution add() {
        log.info(user.getId().toString());
        log.info(aktivita.toString());
        record.setActivityDTO(activityService.getSportActivity(aktivita));
        log.info("setActivity:" + record.getActivityDTO().getName());
        record.setUserDTO(user);
        log.info("setUser" + record.getUserDTO().getFirstName());

        srs.create(record);

        return new RedirectResolution(this.getClass(), "list")
                .addParameter("user.id", user.getId());
    }

    public Resolution delete() {
        String ids = getContext().getRequest().getParameter("user.id");
        log.info("******************************************");
        log.info(ids);
        log.info("******************************************");
        user = userService.getByID(Long.parseLong(ids));
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(record.getId().toString());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        record = srs.getSportRecord(record.getId());
        log.info(record.getId().toString());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
