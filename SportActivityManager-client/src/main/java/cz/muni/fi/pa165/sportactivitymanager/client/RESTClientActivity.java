package cz.muni.fi.pa165.sportactivitymanager.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import cz.muni.fi.pa165.sportactivitymanager.dto.SportActivityDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.CaloriesTableDTO;
import cz.muni.fi.pa165.sportactivitymanager.dto.Gender;
import java.util.Scanner;

/*
 * URL: http://localhost:8080/pa165/rest/activity/...
 * TODO: delete( by id), delete(by activity), get( by name), update(activity)
 */
public class RESTClientActivity {

    private String urlActivity = "http://localhost:8080/pa165/rest/activity/";
    
        //objekt Activity na vstupu       
        public void createActivity(SportActivityDTO activity) {
                try {
                        ClientConfig clientConfig = new DefaultClientConfig();
                        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
                        Client client = Client.create(clientConfig);               
			
                                                            //  pri použítí web.xml:    //artifactIDProjektu/nazav z web.xml/cesta v RestServer tride
			WebResource webResource = client.resource(urlActivity +"create");
//                        String input = "{\"name\":\"jmenoAktivity\"}";
                        //TODO přířazení caloriesTable - to nakonec udela Michal v Konzoli
                               
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, activity);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			System.out.println("Output of method Create from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
        }                
          //String na vstupu      
//        public void createActivity(String input) {
//                try {
//			Client client = Client.create();
//                                                            //  pri použítí web.xml:    //artifactIDProjektu/nazav z web.xml/cesta v RestServer tride
//			WebResource webResource = client.resource(urlActivity +"create");
////                        String input = "{\"name\":\"jmenoAktivity\"}";
//                        //TODO přířazení caloriesTable
//			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//			}
//			System.out.println("Output of method Create from Server .... \n");
//			String output = response.getEntity(String.class);
//			System.out.println(output);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        }
    
        public void getActivityByID(String sid) {         
                try {
			Client client = Client.create();
			WebResource webResource = client.resource(urlActivity +"getID/"+ sid);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
                        String output = response.getEntity(String.class);
			System.out.println("Output of method getByID from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
        }
        
              public void getActivityByName(String name) {         
                try {
			Client client = Client.create();
			WebResource webResource = client.resource(urlActivity +"getName/"+ name);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
                        String output = response.getEntity(String.class);
			System.out.println("Output of method getByID from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
        }
                
              //change to menthods:
               //  Delete ID=2
//                try {
//			Client client = Client.create();
//			WebResource webResource = client.resource("http://localhost:8080/pa165/rest/activity/delete/2");
//			ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//			}
//                        String output = response.getEntity(String.class);
//			System.out.println("Output of method findAll from Server .... \n");
//			System.out.println(output);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//                
//                // GET findAll
//                try {
//			Client client = Client.create();
//			WebResource webResource = client.resource("http://localhost:8080/pa165/rest/activity/all");
//			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//			}
//                        String output = response.getEntity(String.class);
//			System.out.println("Output of method findAll from Server .... \n");
//			System.out.println(output);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
}