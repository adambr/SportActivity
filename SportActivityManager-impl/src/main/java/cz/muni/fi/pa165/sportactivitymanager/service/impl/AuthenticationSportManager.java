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
 * @author Dobeš Kuba
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

        if (auth == null) {
            throw new BadCredentialsException("Bad Credentials");
        }

        String login = auth.getName();
        String password = auth.getCredentials().toString();


        //SEKCE - overeni ADMINA:
        
        User fromDB = userDAO.getByLogin(login);  
       //   if(fromDB == null && userDAO.findAll().isEmpty()){
        if (login.equals("admin") && password.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("USER")); //TODO zmenit USER na ADMIN
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
        }
        //   throw new BadCredentialsException("User with given login not exists");
        //    }
        //     return null;


        //SEKCE - overeni ostatnich uzivatelu: 

        //JEN pro kontrolu co vraci metoda getByLogin - pro ostrou verzi SMAZAT
        System.out.println("#############HESLOOOOOOOOOOOOOOOOOOOOOOO########################");
        System.out.println(fromDB.getPassword());

        //kuba: kdyz se zadane heslo rovná "fromDB" rovnaji tak se mu priradi vsechny credentials co se najdou v seznamu
        if (password.equals(fromDB.getPassword())) {

            // for(int i = 0; i < fromDB.getCredentials().size(); i++){
            authorities.add(new SimpleGrantedAuthority(fromDB.getCredentials().toUpperCase()));
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
        }

        throw new BadCredentialsException("Bad Credentials");
    }

    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}
