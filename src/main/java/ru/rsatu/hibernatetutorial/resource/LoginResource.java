package ru.rsatu.hibernatetutorial.resource;

import ru.rsatu.hibernatetutorial.service.LoginService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/login/api/v1")
public class LoginResource {
    @Inject
    LoginService loginService;

    @GET
    @RolesAllowed({"user", "admin"})
    @Path("/login")
    public String login(@Context SecurityContext securityContext) {
        return loginService.getUser(securityContext.getUserPrincipal().getName()).get(0).role;
    }
}