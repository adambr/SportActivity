/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager;

import org.springframework.dao.DataAccessException;

/**
 * @author Dobes Kuba
 */
public class DataAccException extends DataAccessException{
    
    public DataAccException(String msg){
        super(msg);
    }
    
    public DataAccException(String msg, Throwable cause){
        super(msg, cause);
    }
}
