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
        String ids = getContext().getRequest().getParameter("user.id");
        log.info("******************************************");
        log.info(ids);
        log.info("******************************************");
        user = userService.getByID(Long.parseLong(ids));
        records = srs.findAll();
        activity = activityService.findAll();        
        return new ForwardResolution("/record/list.jsp");
    }

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "duration", required = true),
        @Validate(on = {"add", "save"}, field = "distance", required = true),
        @Validate(on = {"add", "save"}, field = "activityDTO", required = true),
        @Validate(on = {"add", "save"}, field = "startTime", required = true)
    })
    private SportRecordDTO record;

    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"add"})
    public void loadBookFromDatabase() {
        String ids = getContext().getRequest().getParameter("user.id");
        log.info(ids);
        if (ids == null) return;
        user = userService.getByID(Long.parseLong(ids));
        record.setUserDTO(user);
        //add activity
    }
    
    public Resolution add() {
        
        srs.create(record);
//        sr.getUserDTO()
        //getContext().getMessages().add(new LocalizableMessage("book.add.message",escapeHTML(sr.getTitle()),escapeHTML(book.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }

//    
//    private UserService userService;
//    private UserDTO user;
//    /**
//     * User Editing part
//    **/
//    //1. metoda pro Edit
//    //anotace before vytahne data predem z databaze
//    // vytáhu si usera a ID. Je to predtím než se data Bindují, proto dostanu ID jako řetězec a musím si ho převést na Long.
//    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "cancel"})
//    public void loadBookFromDatabase() {
//        String ids = getContext().getRequest().getParameter("user.id");
//        if (ids == null) return;
//        
//        srs.getSportRecord(Long.MIN_VALUE);
//        user = userService.getByID(Long.parseLong(ids));
//        //projít foreachem všechny recordy a musí se shodovat jejich rocord-user.id s předaným user.id
//        List<SportRecordDTO> records2 = new ArrayList<>();
//        for (SportRecordDTO record : records){
//            if (record.getId() == user.getId()){
//                records2.add(record);
//            }
//        }
//    }   
    //2. metoda pro Edit - jsou potřeba obě
    public Resolution edit() {
        log.debug("edit() user={}", record);
        return new ForwardResolution("/record/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
