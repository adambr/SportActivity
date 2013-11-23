/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.web;

import cz.muni.fi.pa165.sportactivitymanager.Gender;
import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.CaloriesTableService;
import cz.muni.fi.pa165.sportactivitymanager.service.SportActivityService;
import static cz.muni.fi.pa165.sportactivitymanager.web.BaseActionBean.escapeHTML;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Petaniss
 */
@UrlBinding("/activities/{$event}/{activity.id}")
public class ActivityActionBean extends BaseActionBean implements ValidationErrorHandler {
    
    final static Logger log = LoggerFactory.getLogger(ActivityActionBean.class);

    @SpringBean
    protected SportActivityService activityService;
    
    @SpringBean
    protected CaloriesTableService caloriesService;
    
    private List<SportActivityDTO> activities;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name",
            required = true,
            minlength = 2,
            maxlength = 30)
    })
    private SportActivityDTO activity;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "calories60Kg", 
            required = true),
        @Validate(on = {"add", "save"}, field = "calories70Kg", 
            required = true),
        @Validate(on = {"add", "save"}, field = "calories80Kg", 
            required = true),
        @Validate(on = {"add", "save"}, field = "calories90Kg", 
            required = true)
    })
    private CaloriesTableDTO calories;

    public CaloriesTableService getCaloriesService() {
        return caloriesService;
    }

    public void setCaloriesService(CaloriesTableService caloriesService) {
        this.caloriesService = caloriesService;
    }

    public CaloriesTableDTO getCalories() {
        return calories;
    }

    public void setCalories(CaloriesTableDTO calories) {
        this.calories = calories;
    }

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
        log.info("@@@@@@@@@@@@@@@@@@@@@@");
        activities = activityService.findAll();
        return new ForwardResolution("/activity/list.jsp");
    }

    public Resolution add() {
        calories.setGender(Gender.MALE);
        caloriesService.create(calories);
        activity.setCalories(calories);
        activityService.create(activity);
        getContext().getMessages().add(new LocalizableMessage("activity.add.message", escapeHTML(activity.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        activity = activityService.getSportActivity(activity.getId());
        activityService.delete(activity);
        getContext().getMessages().add(new LocalizableMessage("activity.del.message", escapeHTML(activity.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit"})
    public void loadBookFromDatabase() {
        String ids = getContext().getRequest().getParameter("activity.id");
        if (ids == null) return;
        activity = activityService.getSportActivity(Long.parseLong(ids));
        calories = activity.getCalories();
    }
    
    public Resolution edit() {
        return new ForwardResolution("/activity/edit.jsp");
    }
    
    public Resolution save() {
        activityService.update(activity);
        caloriesService.update(calories);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        activities = activityService.findAll();
        return null;
    }
}
