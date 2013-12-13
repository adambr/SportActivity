package cz.muni.fi.pa165.sportactivitymanager.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dobes Kuba, Petr Jelínek (refaktor)
 *
 */
public class RESTClientActivity {

    final static Logger log = LoggerFactory.getLogger(RESTClientActivity.class);
    private final static String urlActivity = "http://localhost:8080/pa165/rest/activity/";
    private Client client;

    public RESTClientActivity() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(clientConfig);
    }

    public SportActivityDTO createActivity(SportActivityDTO activity) {
        try {
            WebResource webResource = client.resource(urlActivity + "create");
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, activity);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(SportActivityDTO.class);

        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public SportActivityDTO getActivityByID(String id) {
        try {
            WebResource webResource = client.resource(urlActivity + "getByID/" + id);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(SportActivityDTO.class);

        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public SportActivityDTO getActivityByName(String name) {
        try {
            WebResource webResource = client.resource(urlActivity + "getByName/" + name);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(SportActivityDTO.class);

        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public void deleteActivityByID(long id) {
        try {
            WebResource webResource = client.resource(urlActivity + "deleteByID/" + id);
            ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
            if (response.getStatus() != 204) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public void deleteActivityByActivity(SportActivityDTO activity) {
        try {
            WebResource webResource = client.resource(urlActivity + "deleteByActivity");
            ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class, activity);
            if (response.getStatus() != 204) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public SportActivityDTO updateActivityByActivity(SportActivityDTO activity) {
        try {
            WebResource webResource = client.resource(urlActivity + "update");
            ClientResponse response = webResource.accept("application/json").put(ClientResponse.class, activity);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(SportActivityDTO.class);
            
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public List<SportActivityDTO> findAllActivity() {
        try {
            WebResource webResource = client.resource(urlActivity + "all");
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            return response.getEntity(new GenericType<List<SportActivityDTO>>() {
            });
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
}