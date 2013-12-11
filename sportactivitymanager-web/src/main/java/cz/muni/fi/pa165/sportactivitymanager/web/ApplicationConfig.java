//package cz.muni.fi.pa165.sportactivitymanager.web;
// 
//import java.util.Set;
//import javax.ws.rs.core.Application;
// 
//@javax.ws.rs.ApplicationPath("webresources")
//public class ApplicationConfig extends Application {
// 
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
//        addRestResourceClasses(resources);
//        return resources;
//    }
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(cz.muni.fi.pa165.sportactivitymanager.web.ActivityREST.class);
//        resources.add(org.glassfish.jersey.client.filter.HttpDigestAuthFilter.class);
//        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
//        
//    }
//}

// je potřeba odkomentovat Dependency na Glassfish.jersey