
package za.co.merits.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import za.co.merits.bean.PersonFacade;

/**
 * REST Web Service
 *
 * @author vusa
 */
@Stateless
@Path("users")
public class UsersResource {
    
    @EJB
    PersonFacade personFacade;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersResource
     */
    public UsersResource() {
    }
    
    @GET
    @Path("count")
    public String getUserCount(){
        return ""+ personFacade.count();
    }

    /**
     * Retrieves representation of an instance of com.test.rest.UsersResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        return "json here";
    }

    /**
     * PUT method for updating or creating an instance of UsersResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
