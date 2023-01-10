package ru.rsatu.hibernatetutorial.resource;


import ru.rsatu.hibernatetutorial.pojo.dto.StorageDto;
import ru.rsatu.hibernatetutorial.pojo.entity.Product;
import ru.rsatu.hibernatetutorial.service.ProductService;
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
    public List<StorageDto> loadProductList() {
        return storageService.loadStorageList();
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/saveStorage")
    public StorageDto saveProduct(StorageDto storageDto) {
        return storageService.saveStorage(storageDto);
    }

    @DELETE
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteStorage")
    public List<StorageDto> deleteStorage(@QueryParam("id") Long id) {
        return storageService.deleteStorage(id);
    }
}
