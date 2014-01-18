package cz.muni.fi.pa165.sportactivitymanager.web.tools;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author Petr Jelínek
 */
public class AuthTool {

    public static boolean isRole(Authentication auth, String role) {
        List<GrantedAuthority> roles;
        roles = (List<GrantedAuthority>) auth.getAuthorities();

        return roles.contains(new SimpleGrantedAuthority(role));
    }
}
