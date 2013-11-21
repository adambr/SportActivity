package cz.muni.fi.pa165.sportactivitymanager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import cz.muni.fi.pa165.sportactivitymanager.

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args )
    {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sport");
         
    }
}
