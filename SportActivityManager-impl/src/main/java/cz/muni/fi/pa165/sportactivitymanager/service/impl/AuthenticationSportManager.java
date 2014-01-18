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

    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        if (auth == null) {
            throw new BadCredentialsException("Bad Credentials");
        }

        String login = auth.getName();
        String password = auth.getCredentials().toString();


        //SEKCE - overeni ADMINA:
        if (login.equals("admin") && password.equals("admin")) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
        }


        //SEKCE - overeni ostatnich Uzivatelu: 
        User fromDB = userDAO.getByLogin(login);
        if (fromDB == null) {
            throw new BadCredentialsException("Bad Credentials");
        }
        //JEN pro kontrolu co vraci metoda getByLogin - pro ostrou verzi SMAZAT
        System.out.println("#############HESLOOOOOOOOOOOOOOOOOOOOOOO########################");
        System.out.println(fromDB.getPassword());

        //když se zadané heslo a heslo usera "fromDB" rovnaji, tak se mu priradi vsechny credentials co se najdou v seznamu
        if (password.equals(fromDB.getPassword())) {
            //uzivateli přiřadí roli podle toho jakou má v atributu "credentials" (ten se vyplni pri vytvářrni nového usera)
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(fromDB.getCredentials().toUpperCase()));
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}
