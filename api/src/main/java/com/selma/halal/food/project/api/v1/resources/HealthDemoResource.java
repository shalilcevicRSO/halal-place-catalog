package com.selma.halal.food.project.api.v1.resources;

import com.selma.halal.food.project.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HealthDemoResource {

    private Logger log = Logger.getLogger(HealthDemoResource.class.getName());

    @Inject
    private RestProperties restProperties;

    @GET
    @Path("/break")
    public Response getUnhealthy() {

        Boolean broken = restProperties.getBroken();

        return Response.status(Response.Status.OK).entity(broken).build();

    }

    @POST
    @Path("/break")
    public Response makeUnhealthy() {
        restProperties.setBroken(true);

        return Response.status(Response.Status.OK).entity(restProperties.getBroken()).build();
    }
}
