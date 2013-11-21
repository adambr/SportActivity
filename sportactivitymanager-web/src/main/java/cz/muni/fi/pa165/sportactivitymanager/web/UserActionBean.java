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

//říká na jaká URL to reaguje- ty co začínají /users
//za lomítkem je název metody, která se má zavolat (list, add...)
//v url může být ID usera
@UrlBinding("/users/{$event}/{user.id}")
public class UserActionBean extends BaseActionBean implements ValidationErrorHandler {
 
    //ve web2 se ActionBean implementuje v BaseActionBean a pak se context pouzíva jinak
    
    final static Logger log = LoggerFactory.getLogger(UserActionBean.class);
 
 //   private ActionBeanContext context;
    private UserDTO user;    
    
    //Spring injektuje instanci userServiceImpl a já se nemusím starat o to kde se vezme (viz web.xml)
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
     
    
    //zajišťuje zobrazení stránky s tabulkou
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        usersList = userService.findAll();
        //předání řízení jsp stránce
        return new ForwardResolution("/users/list.jsp");
    }
        
   /** --- part for adding a user 
     * a ověření vyplnění vstupu
     * reguired: true - musi to byt vyplnene
     * minvalue - min. hodnota = tím se vynutí i vstup jako číslo
     */
    
  /**field = název pole z form(formulář pro vkládání knih/uzivatelů) 
    * název ve filed se musí shodovat s name z form:  <td><s:text id="b1" name="user.firstname"/></td>
    *   
    *   validuje to při volání metody add a save
    **/
    @ValidateNestedProperties(value = {        
            @Validate(on = {"add", "save"}, field = "firstname", required = true),
            @Validate(on = {"add", "save"}, field = "lastname", required = true),
            @Validate(on = {"add", "save"}, field = "birthday", required = true),
            @Validate(on = {"add", "save"}, field = "weight", required = true, minvalue = 1  ),
            @Validate(on = {"add", "save"}, field = "gender", required = true)
    })
    
    /**
     * Save user part
    **/
    
    //escapeHTML - aby uživatel nevkládal žádny ošklivý znaky(javascript) - nespustí se
    //tlacitko "Vytvořit nového uživatele"
    //user je asi stejnej user co je ve form.jsp  user.něco
    //  Tak už nemusím vytvářet nového usera a přiřazovat mu jméno... a pak ho uložit pomocí Service, ale je už vytvořen z form.jsp
    
    // vezme obsah proměnné user. a ten obsahuje data z formuláře - to zajistí STRIPES automaticky. Z formuláře pozná že mám položku firstname a zjistí že třída User obsahuje metodu setfirstName a položku nastaví.
    public Resolution add() {
        log.debug("add() user={}", user);
        try{
            userService.create(user);
        }catch(Exception ex){
            log.error("exception during user creation: " + ex);
        }
        //výpis že byl uživatel přidán
        getContext().getMessages().add(new LocalizableMessage("user.add.message",escapeHTML(user.getFirstName()),escapeHTML(user.getLastName())));
        //redirect aby mohl uživatel mačkat Reload a už se user znovu neuložil.
        return new RedirectResolution(this.getClass(), "list");
    }
     
   /**
     * User Editing part
    **/

    //1. metoda pro Edit
    //anotace before vytahne data predem z databaze
    // vytáhu si usera a ID. Je to predtím než se data Bindují, proto dostanu ID jako řetězec a musím si ho převést na Long.
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "cancel"})
    public void loadBookFromDatabase() {
        String ids = getContext().getRequest().getParameter("user.id");
        if (ids == null) return;
        user = userService.getByID(Long.parseLong(ids));
    }
   
    //2. metoda pro Edit - jsou potřeba obě
    public Resolution edit() {
        log.debug("edit() user={}", user);
        return new ForwardResolution("/user/list.jsp");
     }
    
    //submitovací metoda pro save
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
        // tlačítko delete předává 2 parametry: delete a user.id
        // podle id vytvořím usera a toho smažu
        log.debug("delete({})", user.getId());
        user = userService.getByID(user.getId());
        try{
            userService.delete(user);
        }catch(Exception ex){
            return new StreamingResolution("Delete of user with ID "+user.getId()+" was not successfull.");
        }
        //vypise ze user jmeno prijmeni byl smazán. jmeno a prijmeni se bere z formulare a vklada se do textu "book.delete.message" z lokalizace.
        getContext().getMessages().add(new LocalizableMessage("user.delete.message",escapeHTML(user.getFirstName()),escapeHTML(user.getLastName())));
        //znovu vypise seznam knih, aby uživatel mohl mačkat Reload
        return new RedirectResolution(this.getClass(), "list");
    }    

    //podle web2
    //po implementaci Validation bylo potreba pridat tuto tridu, 
    //když neproběhla Validace správně tak se musí zde uložit seznam chyb.
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
    //fill up the data for the table if validation errors occured
        usersList = userService.findAll();
        //return null to let the event handling continue
        return null;
    }
    
}

