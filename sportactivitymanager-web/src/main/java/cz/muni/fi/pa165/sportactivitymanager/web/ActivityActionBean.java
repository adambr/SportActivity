/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.web;

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
 * @author Petaniss
 */
@UrlBinding("/activities/{$event}/{activity.id}")
public class ActivityActionBean extends BaseActionBean implements ValidationErrorHandler {

    private List<String> activities;

    public List<String> getActivities() {
        return activities;
    }
    
    @DefaultHandler
    public Resolution list() {
        activities = new ArrayList<String>();
        activities.add("test1");
        activities.add("peta");
        activities.add("adam");
        activities.add("michal");
        activities.add("kuba");
        return new ForwardResolution("/activity/list.jsp");
    }
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
