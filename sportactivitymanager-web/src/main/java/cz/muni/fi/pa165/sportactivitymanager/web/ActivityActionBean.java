/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.web;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportActivityService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Petaniss
 */
@UrlBinding("/activities/{$event}/{activity.id}")
public class ActivityActionBean extends BaseActionBean implements ValidationErrorHandler {
    
    @SpringBean
    protected SportActivityService activityService;
    
   
    private List<SportActivityDTO> activities;

    public List<SportActivityDTO> getActivities() {
        return activities;
    }
    
    @DefaultHandler
    public Resolution list() {
        activities = activityService.findAll();
        return new ForwardResolution("/activity/list.jsp");
    }
    
    public Resolution add() {
        SportActivityDTO act = new SportActivityDTO();
        act.setName("Plavání");
        activityService.create(act);
        return new ForwardResolution("/activity/add.jsp");
    }
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
