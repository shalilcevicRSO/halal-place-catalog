package com.selma.halal.food.project.api.v1.resources;

import com.selma.halal.food.project.services.beans.HalalPlaceMetadataBean;
import com.selma.halal.food.project.lib.HalalPlaceMetadata;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;


//@ApplicationScoped
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/places")
public class HalalPlaceMetadataResource {

    private Logger log = Logger.getLogger(HalalPlaceMetadataResource.class.getName());

    @Inject
    private HalalPlaceMetadataBean halalPlaceMetadataBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getHalalPlaceMetadata() {

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

}