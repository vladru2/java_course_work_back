package ru.rsatu.hibernatetutorial.resource;


import ru.rsatu.hibernatetutorial.pojo.dto.LoadStorageDto;
import ru.rsatu.hibernatetutorial.pojo.dto.StorageDto;
import ru.rsatu.hibernatetutorial.pojo.entity.Storage;
import ru.rsatu.hibernatetutorial.service.StorageService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/storage/api/v1")
public class StorageResource {

    @Inject
    StorageService storageService;

    @GET
    @PermitAll
    @Path("/loadStorageList")
    public LoadStorageDto loadStorageList(@QueryParam("from") int from, @QueryParam("to") int to) {
        return storageService.loadStorageList(from, to);
    }

    @GET
    @PermitAll
    @Path("/loadStorageList2")
    public LoadStorageDto loadStorageList2(@QueryParam("from") int from, @QueryParam("to") int to, StorageDto storageDto) {
        return storageService.loadStorageList2(from, to, storageDto);
    }

    @POST
    @RolesAllowed({"user", "admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/saveStorage")
    public StorageDto saveStorage(StorageDto storageDto) {
        return storageService.saveStorage(storageDto);
    }

    @DELETE
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteStorage")
    public LoadStorageDto deleteStorage(@QueryParam("id") Long id, @QueryParam("from") int from, @QueryParam("to") int to) {
        return storageService.deleteStorage(id, from, to);
    }
}