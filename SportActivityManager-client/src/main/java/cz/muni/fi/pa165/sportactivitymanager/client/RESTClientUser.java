package cz.muni.fi.pa165.sportactivitymanager.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import static cz.muni.fi.pa165.sportactivitymanager.client.RESTClientUser.log;
import cz.muni.fi.pa165.sportactivitymanager.dto.UserDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dobes Kuba
 *
 */
public class RESTClientUser {

    final static Logger log = LoggerFactory.getLogger(RESTClientActivity.class);
    private final static String urlUser = "http://localhost:8080/pa165/rest/user/";
    private Client client;

    public RESTClientUser() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(clientConfig);
    }

    public UserDTO createUser(UserDTO user) {
        try {
            WebResource webResource = client.resource(urlUser + "create");
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, user);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(UserDTO.class);

        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public UserDTO getUserByID(String id) {
        try {
            WebResource webResource = client.resource(urlUser + "getByID/" + id);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(UserDTO.class);

        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public void deleteUserByUser(UserDTO user) {
        try {
            WebResource webResource = client.resource(urlUser + "deleteByUser");
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, user);
            if (response.getStatus() != 204) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public UserDTO updateUserByUser(UserDTO user) {
        try {
            WebResource webResource = client.resource(urlUser + "update");
            ClientResponse response = webResource.type("application/json")
                    .accept("application/json").post(ClientResponse.class, user);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(UserDTO.class);
            
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public List<UserDTO> findAllUsers() {
        try {
            WebResource webResource = client.resource(urlUser + "all");
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(new GenericType<List<UserDTO>>() {
            });
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
}