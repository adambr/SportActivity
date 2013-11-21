package cz.muni.fi.pa165.sportactivitymanager.web;
 
import cz.muni.fi.pa165.sportactivitymanager.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
 
import java.util.List;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

import static cz.muni.fi.pa165.sportactivitymanager.web.BaseActionBean.escapeHTML;
import net.sourceforge.stripes.controller.LifecycleStage;
/*
 * @author tempest
 * ValidationErrorHandler slouží pro ověření vyplněných vstupů
 */

@UrlBinding("/users/{$event}/{user.id}")
public class UserActionBean extends BaseActionBean implements ValidationErrorHandler {
 
    //ve web2 se ActionBean implementuje v BaseActionBean a pak se context pouzíva jinak
    
    final static Logger log = LoggerFactory.getLogger(UserActionBean.class);
 
 //   private ActionBeanContext context;
    private UserDTO user;    
    @SpringBean
    protected UserService userService;
   
    //podle web2, pro zobrazení seznamu uživatelů
    private List<UserDTO> usersList;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }        
     
    //podle web2:
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        usersList = userService.findAll();
        return new ForwardResolution("/users/list.jsp");
    }
        
   /** --- part for adding a user 
     * a ověření vyplnění vstupu
     * reguired: true - musi to byt vyplnene
     * minvalue - min. hodnota
     */
    
  /**field = název pole z form(formulář pro vkládání knih/uzivatelů) 
    * název ve filed se musí shodovat s name z form:  <td><s:text id="b1" name="user.firstname"/></td>
    *   
    *   validuje to pri add a save
    **/
    @ValidateNestedProperties(value = {        
            @Validate(on = {"add", "save"}, field = "firstname", required = true),
            @Validate(on = {"add", "save"}, field = "lastname", required = true),
            @Validate(on = {"add", "save"}, field = "birthday", required = true),
            @Validate(on = {"add", "save"}, field = "weight", required = true),
            @Validate(on = {"add", "save"}, field = "gender", required = true)
    })
    
    /**
     * Save user part
    **/
    
    //escapeHTML - aby uzivatel nevkladal zadny osklivy znaky(javascript) - nespusti se
    //tlacitko "Vytvořit nového uživatele"
    //user je asi stejnej user co je ve form.jsp  user.něco
    //  Tak už nemusím vytvářet nového usera a přiřazovat mu jméno... a pak ho uložit pomocí Service, ale je už vytvořen z form.jsp
    public Resolution add() {
        log.debug("add() user={}", user);
        try{
            userService.create(user);
        }catch(Exception ex){
            log.error("exception during user creation: " + ex);
        }
        //výpis že byl uživatel přidán
        getContext().getMessages().add(new LocalizableMessage("user.add.message",escapeHTML(user.getFirstName()),escapeHTML(user.getLastName())));
        return new RedirectResolution(this.getClass(), "list");
    }
     
   /**
     * User Editing part
    **/

    //anotace before vytahne data predem z databaze
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "cancel"})
    public void loadBookFromDatabase() {
        String ids = getContext().getRequest().getParameter("user.id");
        if (ids == null) return;
        user = userService.getByID(Long.parseLong(ids));
    }
    
    //web2
    //TODO
    //co to dělá?
    public Resolution edit() {
        log.debug("edit() user={}", user);
        
        //DO ? dodělat update?´, nebo ukládání změněných hodnot funguje jinak?
        //        - update je až u SAVE - až u toho tlačíka
//        try{
//            userService.update(newUser);
//        }catch(Exception ex){
//            log.error("Exception during user update: " + ex);
//        }
        
        return new ForwardResolution("/user/list.jsp");
     }
    
    //tlačítko pro SAVE
    public Resolution save() {
        log.debug("save() user={}", user);
        userService.update(user);
        return new RedirectResolution(this.getClass(), "list");
    }
    //tlactiko pro CANCEL (Task 2)
    //TODO
     public Resolution cancel() {
    //    log.debug("cancel() book={}", book);
        return new ForwardResolution("/book/list.jsp");
    }
     
    public List<UserDTO> getUsersList() {
        return userService.findAll();
    }
    
    /**
     * Deletion part
    **/
    public Resolution delete(){
        
        log.debug("delete({})", user.getId());
        try{
            userService.delete(user);
        }catch(Exception ex){
            return new StreamingResolution("Delete of user with ID "+user.getId()+" was not successfull.");
        }
        //vypise ze user jmeno prijmeni byl smazán. jmeno a prijmeni se bere z formulare a vklada se do textu "book.delete.message" z lokalizace.
        getContext().getMessages().add(new LocalizableMessage("user.delete.message",escapeHTML(user.getFirstName()),escapeHTML(user.getLastName())));
        //asi znovu vypise seznam knih
        return new RedirectResolution(this.getClass(), "list");
        
        //podle luk
        //tohle nevím co přesně dělá:
     //   getContext().getResponse().setHeader("Delete successfull", "OK");    //nikam na obrazovku to uspěšné smazání nevypisuje
     //   return new StreamingResolution("text/html","OK");
    }    

    //podle web2
    //po implementaci Validation bylo potreba pridat tuto tridu, 
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
    //fill up the data for the table if validation errors occured
        usersList = userService.findAll();
        //return null to let the event handling continue
        return null;
    }
    
}

