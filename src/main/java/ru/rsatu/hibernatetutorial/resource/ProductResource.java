package ru.rsatu.hibernatetutorial.resource;

import ru.rsatu.hibernatetutorial.pojo.entity.Product;
import ru.rsatu.hibernatetutorial.service.ProductService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products/api/v1")
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    @PermitAll
    @Path("/loadProductList")
    public List<Product> loadProductList() {
        return productService.loadProductList();
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/saveProduct")
    public Product saveProduct(Product product) {
        return productService.saveProduct(product);
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateProduct")
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    @DELETE
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteProduct")
    public List<Product> deleteProduct(@QueryParam("id") Long id) {
        return productService.deleteProduct(id);
    }
}