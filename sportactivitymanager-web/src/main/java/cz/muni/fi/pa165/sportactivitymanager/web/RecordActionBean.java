/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.web;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportRecordService;
import cz.muni.fi.pa165.sportactivitymanager.service.impl.SportRecordServiceImpl;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Adam Brauner
 */
@UrlBinding("/records/{$event}/{record.id}")
public class RecordActionBean extends BaseActionBean implements ValidationErrorHandler {
    
    private List<SportRecordDTO> records = new ArrayList<SportRecordDTO>();

    public List<SportRecordDTO> getRecords() {
        return records;
        }
    
     @DefaultHandler
    public Resolution list() {
        SportRecordService sr = new SportRecordServiceImpl();
        records = sr.findAll();
   
        return new ForwardResolution("/record/list.jsp");

    }
    
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
