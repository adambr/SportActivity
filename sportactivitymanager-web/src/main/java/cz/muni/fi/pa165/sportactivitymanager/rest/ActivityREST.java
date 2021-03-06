package cz.muni.fi.pa165.sportactivitymanager.rest;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.inject.Inject;
import cz.muni.fi.pa165.sportactivitymanager.DataAccException;

import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.SportActivityService;
import cz.muni.fi.pa165.sportactivitymanager.service.CaloriesTableService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dobes Kuba, Petr Jelínek
 *
 */
@Component
@Path("activity")
public class ActivityREST {

    @Autowired
    protected SportActivityService activityService;
    @Autowired
    protected CaloriesTableService caloriesService;
    final static Logger log = LoggerFactory.getLogger(ActivityREST.class);
    @Inject
    @InjectParam("org.springframework.security.authenticationManager")
    protected AuthenticationManager authenticationManager;

    @GET
    @Produces("text/plain")
    public String getText() {
        return "Hello, Activity Rest API is ready!";
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SportActivityDTO create(SportActivityDTO activity,
            @Context HttpServletResponse response) throws IOException {

        if (activity == null || activity.getName().isEmpty() || activity.getCalories() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            if (authenticationManager != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken("rest", "rest");
                Authentication result = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(result);
                
                caloriesService.create(activity.getCalories());
                activityService.create(activity);
            } else {
                log.error("authenticationManager is null");
            }
        } catch (DataAccException ex) {
            log.error("Create activity error: " + ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return activity;
    }

    @GET
    @Path("getByID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SportActivityDTO getByID(@PathParam("id") String id,
            @Context HttpServletResponse response) throws IOException {

        Long lid = Long.parseLong(id);
        SportActivityDTO activity = null;
        try {
            if (authenticationManager != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken("rest", "rest");
                Authentication result = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(result);

                activity = activityService.getSportActivity(lid);
            } else {
                log.error("authenticationManager is null");
            }
        } catch (DataAccException ex) {
            log.error("Get activity by id error: " + ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        if (activity == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "id_not_found");
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return activity;
    }

    @GET
    @Path("getByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public SportActivityDTO getByName(@PathParam("name") String name,
            @Context HttpServletResponse response) throws IOException {

        SportActivityDTO activity = null;
        try {
            if (authenticationManager != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken("rest", "rest");
                Authentication result = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(result);

                activity = activityService.getSportActivity(name);
            } else {
                log.error("authenticationManager is null");
            }
        } catch (DataAccException ex) {
            log.error("Get activity by name error: " + ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        if (activity == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "id_not_found");
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return activity;
    }

    @DELETE
    @Path("deleteByID/{id}")
    public void deleteByID(@PathParam("id") String sid,
            @Context HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(sid);
        SportActivityDTO activity = null;

        if (authenticationManager != null) {
            
            Authentication auth = new UsernamePasswordAuthenticationToken("rest", "rest");
            Authentication result = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(result);

            try {
                activity = activityService.getSportActivity(id);
            } catch (Exception e) {
                log.error("Retrieve activity in remove error: " + e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

            if (activity == null || activity.getId() == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

            try {
                activityService.delete(activity);
            } catch (Exception e) {
                log.error("Update activity error: " + e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            log.error("authenticationManager is null");
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @POST
    @Path("deleteByActivity")
    public void deleteByActivity(SportActivityDTO activity,
            @Context HttpServletResponse response) throws IOException {

        if (activity == null || activity.getId() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            if (authenticationManager != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken("rest", "rest");
                Authentication result = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(result);

                activityService.delete(activity);
            } else {
                log.error("authenticationManager is null");
            }
        } catch (Exception e) {
            log.error("Update activity error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SportActivityDTO update(SportActivityDTO activity,
            @Context HttpServletResponse response) throws IOException {

        if (activity == null || activity.getName().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            if (authenticationManager != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken("rest", "rest");
                Authentication result = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(result);

                if (activity.getCalories() != null) {
                    caloriesService.update(activity.getCalories());
                }
                activityService.update(activity);
            } else {
                log.error("authenticationManager is null");
            }
        } catch (Exception ex) {
            log.error("Update activity error: " + ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return activity;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SportActivityDTO> findAll(@Context HttpServletResponse response) throws IOException {

        List<SportActivityDTO> list = null;
        try {
            if (authenticationManager != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken("rest", "rest");
                Authentication result = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(result);

                if (activityService == null) {
                    log.info("activityService NULL");
                }
                list = activityService.findAll();
            } else {
                log.error("authenticationManager is null");
            }
        } catch (Exception e) {
            log.error("Find all activity error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        if (list == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return list;
    }
}
