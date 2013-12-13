
package cz.muni.fi.pa165.sportactivitymanager.rest;

import cz.muni.fi.pa165.sportactivitymanager.DataAccException;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import cz.muni.fi.pa165.sportactivitymanager.service.UserService;
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
import org.springframework.stereotype.Component;

/**
 *
 * @author Michal Galo
 */
//Delete by ID neni v Service vrstve, tak sem ho smazal i tady
@Component
@Path("user")
public class UserREST
{
    @Autowired
    protected UserService userService;   
    
    final static Logger log = LoggerFactory.getLogger(UserREST.class);

    @GET
    @Produces("text/plain")
    public String getText() 
    {
        return "Hello, User REST API is ready!";
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO create(UserDTO user, @Context HttpServletResponse response) throws IOException 
    {       
        if (user == null || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || 
                user.getBirthDay() == null || user.getWeight() == null || user.getGender() == null)            
        {           
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        try {
            userService.create(user);        
        } catch (DataAccException ex) {
            log.error("Create user error: " + ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return user;
    }

    @GET
    @Path("getByID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getByID(@PathParam("id") String id, 
            @Context HttpServletResponse response) throws IOException {        
        Long lid = Long.parseLong(id);
        UserDTO user = null;        
        try {
            user = userService.getByID(lid);
        } catch (DataAccException ex) {
            log.error("Get user by id error: " + ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "id_not_found");
        }        
        response.setHeader("Access-Control-Allow-Origin", "*");
        return user;
    }  
  
    
    @DELETE
    @Path("deleteByUser")
    public void deleteByUser(UserDTO user, 
            @Context HttpServletResponse response) throws IOException {                
        
        if (user == null || user.getId() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            userService.delete(user);
        } catch (Exception e) {
            log.error("Delete user error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
    
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO update(UserDTO user, 
            @Context HttpServletResponse response) throws IOException {        
        
        if (user == null || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || 
                user.getBirthDay() == null || user.getWeight() == null || user.getGender() == null) 
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        try  {
            userService.update(user);
        } catch (Exception ex) {
            log.error("Update user error: " + ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return user;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> findAll(@Context HttpServletResponse response) throws IOException  {                
       
        List<UserDTO> list = null;        
        try {
            if (userService == null) { log.info("userService is NULL"); }            
            list = userService.findAll();
        } catch (Exception e) {
            log.error("Find all users error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        if (list == null)  {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }        
        response.setHeader("Access-Control-Allow-Origin", "*");
        return list;
    }   
}
