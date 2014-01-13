package cz.muni.fi.pa165.sportactivitymanager.service.impl;

import cz.muni.fi.pa165.sportactivitymanager.User;
import cz.muni.fi.pa165.sportactivitymanager.dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author tempest
 */

public class AuthenticationSportManager implements AuthenticationProvider {

  @Autowired
  private UserDAO userDAO;

  public AuthenticationSportManager() {
  
  }
  
  public void setUserDAO(UserDAO userDAO) {
      this.userDAO = userDAO;
  }
     
  private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
  
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
      
      if(auth == null){
          throw new BadCredentialsException("Bad Credentials");
      }      
      
      String login = auth.getName();
      String password = auth.getCredentials().toString();
      
 //     User fromDB = userDAO.getByLogin(login);  //getByLogin nefunguje - as ije spatná syntax JPQL dotazu
      //dodělat - když bude user s takovým loginem a heslem v DB tak mu přiřadit do authorities oprávnění USER
   //   if(fromDB == null && userDAO.findAll().isEmpty()){
          if(login.equals("admin") && password.equals("admin")){
              authorities.add(new SimpleGrantedAuthority("USER")); //TODO zmenit USER na ADMIN
              return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
          }         
          throw new BadCredentialsException("User with given login not exists");
 //    }
 //     return null;
  }

    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}
