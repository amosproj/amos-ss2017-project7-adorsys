package de.fau.amos.virtualledger.server.api;

import de.fau.amos.virtualledger.dtos.BankAccess;
import de.fau.amos.virtualledger.dtos.StringApiModel;
import de.fau.amos.virtualledger.server.auth.Secured;
import de.fau.amos.virtualledger.server.controllers.BankingOverviewController;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Georg on 20.05.2017.
 */
@Path("/banking")
public class BankingOverviewEndpoint {

    @Inject
    BankingOverviewController bankingOverviewController;


    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankingOverview(@Context SecurityContext securityContext)
    {
        final String email = securityContext.getUserPrincipal().getName();
        List<BankAccess> bankAccesses = bankingOverviewController.getBankingOverview(email);
        return Response.ok(bankAccesses).build();
    }
}