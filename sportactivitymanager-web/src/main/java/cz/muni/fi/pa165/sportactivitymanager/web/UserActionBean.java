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

    Authentication auth;

    public enum MyRole {

        USER, ADMIN;
    }

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

    @Before(on = {"list", "delete"})
    public Resolution authorizationAdmin() {
        Authentication auth;
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (!AuthTool.isRole(auth, ADMIN)) {
            return new RedirectResolution(IndexActionBean.class);
        }

        return null;
    }

    @Before(on = {"edit", "save"})
    public Resolution authorizationUser() {
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
        if (authRole.equals(ADMIN)) {
            String ids = getContext().getRequest().getParameter("user.id");
            Long id;
            try {
                id = Long.parseLong(ids);
            } catch (NumberFormatException ex) {
                return new RedirectResolution(this.getClass());
            }
            user = userService.getByID(id);
        } else {
            user = userService.getByLogin(authName);
        }

        // for edit form select box
        if (user.getCredentials().equals("ADMIN")) {
            selectedRole = MyRole.ADMIN;
        } else {
            selectedRole = MyRole.USER;
        }

        return new ForwardResolution("/user/edit.jsp");
    }

    public Resolution save() {
        boolean logout = false;

        if (authRole.equals(ADMIN)) {
            user.setRecords(userService.getByID(user.getId()).getRecords());
            user.setCredentials(selectedRole.toString());
        } else {
            UserDTO userDB = userService.getByLogin(authName);
            user.setRecords(userService.getByID(userDB.getId()).getRecords());
            user.setCredentials(userDB.getCredentials());
            if (!user.getLogin().equals(userDB.getLogin())) {
                logout = true;
            }
        }

        userService.update(user);
        getContext().getMessages().add(new LocalizableMessage("user.update.message", escapeHTML(user.getFirstName()), escapeHTML(user.getLastName())));

        if (logout) {
            SecurityContextHolder.clearContext();
        }

        if (authRole.equals(ADMIN)) {
            return new RedirectResolution(this.getClass(), "list");
        } else {
            return new RedirectResolution(this.getClass(), "edit");
        }
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
