package ru.rsatu.hibernatetutorial.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/login/api/v1")
public class LoginResource {
    @GET
    @RolesAllowed({"user", "admin"})
    @Path("/login")
    public String login() {
        return "";
    }
}