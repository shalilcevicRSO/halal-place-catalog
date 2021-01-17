package com.selma.halal.food.project.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.selma.halal.food.project.services.beans.HalalPlaceMetadataBean;
import com.selma.halal.food.project.lib.HalalPlaceMetadata;
import org.eclipse.microprofile.metrics.Meter;
import org.eclipse.microprofile.metrics.annotation.Metric;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
//import com.kumuluz.ee.logs.cdi.Log;
//import com.kumuluz.ee.discovery.annotations.DiscoverService;


//@ApplicationScoped
//@Log
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/places")
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class HalalPlaceMetadataResource {

    private Logger log = Logger.getLogger(HalalPlaceMetadataResource.class.getName());

    @Inject
    private HalalPlaceMetadataBean halalPlaceMetadataBean;

    @Context
    protected UriInfo uriInfo;

    @Inject
    @Metric(name = "all_places_request_meter")
    private Meter all_places_request_meter;

    @Inject
    @Metric(name = "create_new_place_meter")
    private Meter create_new_place_meter;

    @GET
    @Path("allPlacesRequests")
    public Response getAllPlacesRequestsMeter() {
        return Response.ok(all_places_request_meter).build();
    }

    @GET
    @Path("createNewPlaceMeter")
    public Response getCreateNewPlaceMeter() {
        return Response.ok(create_new_place_meter).build();
    }


    @GET
    public Response getHalalPlaceMetadata() {

        all_places_request_meter.mark();

        List<HalalPlaceMetadata> halalPlaceMetadata = halalPlaceMetadataBean.getHalalPlaceMetadataFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(halalPlaceMetadata).build();
    }

    @GET
    @Path("/{halalPlaceMetadataId}")
    public Response getHalalPlaceMetadata(@PathParam("halalPlaceMetadataId") Integer halalPlaceMetadataId) {

        HalalPlaceMetadata halalPlaceMetadata = halalPlaceMetadataBean.getHalalPlaceMetadata(halalPlaceMetadataId);

        if (halalPlaceMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(halalPlaceMetadata).build();
    }


    @POST
    public Response createHalalPlaceMetadata(HalalPlaceMetadata halalPlaceMetadata) {

        create_new_place_meter.mark();

        if ((halalPlaceMetadata.getPlaceName() == null || halalPlaceMetadata.getCity() == null || halalPlaceMetadata.getCountry() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            halalPlaceMetadata = halalPlaceMetadataBean.createHalalPlaceMetadata(halalPlaceMetadata);
        }

        return Response.status(Response.Status.OK).entity(halalPlaceMetadata).build();

    }

    @PUT
    @Path("/{halalPlaceMetadataId}")
    public Response putHalalPlaceMetadata(@PathParam("halalPlaceMetadataId") Integer halalPlaceMetadataId,
                                          HalalPlaceMetadata halalPlaceMetadata) {

        halalPlaceMetadata = halalPlaceMetadataBean.putHalalPlaceMetadata(halalPlaceMetadataId, halalPlaceMetadata);

        if (halalPlaceMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    @DELETE
    @Path("/{halalPlaceMetadataId}")
    public Response deleteHalalPlaceMetadata(@PathParam("halalPlaceMetadataId") Integer halalPlaceMetadataId) {

        boolean deleted = halalPlaceMetadataBean.deleteHalalPlaceMetadata(halalPlaceMetadataId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // NEW METHODS ADDED

    @GET
    @Path("/{city}/{country}")
    public Response getSearchHalalPlaceMetadata(
            @PathParam("city") String city,
            @PathParam("country") String country) {

        HalalPlaceMetadata halalPlaceMetadata = halalPlaceMetadataBean.getSearchHalalPlaceMetadata(city, country);

        if (halalPlaceMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(halalPlaceMetadata).build();

    }

    // 3rd Party API
    @GET
    @Path("/weather/{city}")
    public String getCurrentWeather(
            @PathParam("city") String city) {

        HttpResponse<String> response = null;
        try{
            Unirest.setTimeouts(0, 0);
            response =  Unirest.get("http://api.openweathermap.org/data/2.5/weather?q=" +
                    city +
                    "&appid=f786409db597cff4cd0f594a80cb1bad&units=metric")
                    .asString();

        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();


    }

}