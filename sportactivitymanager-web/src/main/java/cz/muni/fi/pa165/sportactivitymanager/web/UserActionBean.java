package cz.muni.fi.pa165.sportactivitymanager.web;

import cz.muni.fi.pa165.sportactivitymanager.dto.*;
import cz.muni.fi.pa165.sportactivitymanager.service.SportRecordService;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
import static cz.muni.fi.pa165.sportactivitymanager.web.BaseActionBean.ADMIN;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

import static cz.muni.fi.pa165.sportactivitymanager.web.BaseActionBean.escapeHTML;
import cz.muni.fi.pa165.sportactivitymanager.web.tools.AuthTool;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Kuba Dobes, Petr Jelínek
 *
 */
@UrlBinding("/users/{$event}/{user.id}")
public class UserActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(UserActionBean.class);

    public enum MyRole {

        USER, ADMIN;
    }

    @Validate(on = {"add", "save"}, required = true)
    private MyRole selectedRole;

    public MyRole getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(MyRole selectedRole) {
        this.selectedRole = selectedRole;
    }

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "firstName", required = true, minlength = 2),
        @Validate(on = {"add", "save"}, field = "lastName", required = true),
        @Validate(on = {"add", "save"}, field = "birthDay", required = true),
        @Validate(on = {"add", "save"}, field = "weight", required = true, minvalue = 1),
        @Validate(on = {"add", "save"}, field = "gender", required = true),
        @Validate(on = {"add", "save"}, field = "login", required = true, minlength = 4),
        @Validate(on = {"add", "save"}, field = "password", required = true, minlength = 4)
    })
    private UserDTO user;

    @ValidationMethod(on = {"add"})
    public void validateAdd(ValidationErrors errors) {
        if (userService.getByLogin(this.user.getLogin()) != null) {
            errors.add("user.login", new LocalizableError("loginTaken"));
        }
    }

    @ValidationMethod(on = {"save"})
    public void validateSave(ValidationErrors errors) {
        UserDTO fromDB = userService.getByID(user.getId());

        if (!user.getLogin().equals(fromDB.getLogin())) {
            if (userService.getByLogin(this.user.getLogin()) != null) {
                errors.add("user.login", new LocalizableError("loginTaken"));
            }
        }
    }

    @SpringBean
    protected SportRecordService srs;

    @SpringBean
    protected UserService userService;

    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Before
    public Resolution authorization() {
        Authentication auth;
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (!AuthTool.isRole(auth, ADMIN)) {
            return new RedirectResolution(IndexActionBean.class);
        }

        return null;
    }

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        users = userService.findAll();

        return new ForwardResolution("/user/list.jsp");
    }

    public Resolution add() {
        user.setCredentials(selectedRole.toString());
        userService.create(user);
        getContext().getMessages().add(new LocalizableMessage("user.add.message", escapeHTML(user.getFirstName()), escapeHTML(user.getLastName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution edit() {
        String ids = getContext().getRequest().getParameter("user.id");
        if (ids == null) {
            return new RedirectResolution(this.getClass());
        }

        try {
            user = userService.getByID(Long.parseLong(ids));
        } catch (NumberFormatException ex) {
            return new RedirectResolution(this.getClass());
        }
        
        if (user.getCredentials().equals("ADMIN")) {
            selectedRole = MyRole.ADMIN;
        } else {
            selectedRole = MyRole.USER;
        }

        return new ForwardResolution("/user/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() user={}", user);
        user.setRecords(userService.getByID(user.getId()).getRecords());
        user.setCredentials(selectedRole.toString());
        userService.update(user);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new ForwardResolution("/user/list.jsp");
    }

    public Resolution delete() {
        user = userService.getByID(user.getId());
        userService.delete(user);

        getContext().getMessages().add(new LocalizableMessage("user.delete.message", escapeHTML(user.getFirstName()), escapeHTML(user.getLastName())));

        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        users = userService.findAll();

        return null;
    }
}
