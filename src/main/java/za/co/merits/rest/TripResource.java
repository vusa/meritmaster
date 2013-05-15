package za.co.merits.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created with IntelliJ IDEA.
 * Person: vusa
 * Date: 2012/09/16
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@Path("trip")
public class TripResource {

    @GET
    @Path("create")
    public String createTrip(String name){
        return "success";
    }

    @GET
    @Produces("application/xml")
    public String getXml() {
        return "<trip />";
    }

    @GET
    @Produces("application/json")
    public String getJson() {
        return "json here";
    }
}
