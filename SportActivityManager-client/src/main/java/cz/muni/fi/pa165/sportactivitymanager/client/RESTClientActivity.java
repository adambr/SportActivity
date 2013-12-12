package cz.muni.fi.pa165.sportactivitymanager.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
 * URL: http://localhost:8080/pa165/rest/activity/...
 */
public class RESTClientActivity {

	public static void main(String[] args) {
		//POST create
                try {
			Client client = Client.create();
                                                            //  pri použítí web.xml:    //artifactIDProjektu/nazav z web.xml/cesta v RestServer tride
			WebResource webResource = client.resource("http://localhost:8080/pa165/rest/activity/create");
                        String input = "{\"name\":\"jmenoAktivity\"}";
                        //TODO přířazení caloriesTable
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();

		}
                //GET 
//                try {
//			Client client = Client.create();
//			WebResource webResource = client.resource("http://localhost:8080/sportactivitymanager-web/webresources");
//			ClientResponse response = webResource.accept("activity/getXXX/1").get(ClientResponse.class);
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//			}
//                        String output = response.getEntity(String.class);
//			System.out.println("Output from Server .... \n");
//			System.out.println(output);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}