package com.amproductions.sharemicroservice;

import javax.swing.text.Document;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("share")
public class ShareResources {

    @GET
    public Response getUsers(@QueryParam("userId") String userId) {
        try {
            System.out.println(userId);
            Object sharedPhotos = Database.GetSharedPhotos(userId);

            if (sharedPhotos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.OK).entity(sharedPhotos).build();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    public Response shareOnePhoto(ShareEntry addShare) {
        try {

            if(Database.AddShareToPhoto(addShare)){
                return Response.status(Response.Status.CREATED).build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
