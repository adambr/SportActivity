/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.web;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportRecordDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportActivityService;
import cz.muni.fi.pa165.sportactivitymanager.service.SportRecordService;
import cz.muni.fi.pa165.sportactivitymanager.service.impl.SportRecordServiceImpl;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adam Brauner
 */
@UrlBinding("/records/{$event}/{record.id}")
public class RecordActionBean extends BaseActionBean implements ValidationErrorHandler {
   final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);
    
    @SpringBean
    protected SportRecordService srs;
    
    private List<SportRecordDTO> records ;

    public List<SportRecordDTO> getRecords() {
        return records;
        }
    
     @DefaultHandler
    public Resolution list() {
        records = srs.findAll();   
        return new ForwardResolution("/record/list.jsp");

    }
     
         //--- part for adding a book ----

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "user", required = true),
            @Validate(on = {"add", "save"}, field = "duration", required = true),
            @Validate(on = {"add", "save"}, field = "distance", required = true),
            @Validate(on = {"add", "save"}, field = "startTime", required = true)
    })    
    
    private SportRecordDTO sr;

    public Resolution add() {
        log.debug("add() book={}", sr);
        srs.create(sr);
        //getContext().getMessages().add(new LocalizableMessage("book.add.message",escapeHTML(sr.getTitle()),escapeHTML(book.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
