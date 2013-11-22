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
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
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
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true,
                minlength = 2,
                maxlength = 30)
    })
    private SportActivityDTO activity;

    public List<SportActivityDTO> getActivities() {
        return activities;
    }

    public SportActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(SportActivityService activityService) {
        this.activityService = activityService;
    }

    public SportActivityDTO getActivity() {
        return activity;
    }

    public void setActivity(SportActivityDTO activity) {
        this.activity = activity;
    }

    @DefaultHandler
    public Resolution list() {
        activities = activityService.findAll();
        return new ForwardResolution("/activity/list.jsp");
    }

    public Resolution add() {
        activityService.create(activity);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        activities = activityService.findAll();
        return null;
    }
}
