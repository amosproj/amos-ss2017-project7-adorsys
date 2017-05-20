package de.fau.amos.virtualledger.server.banking.api.userEndpoint;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import de.fau.amos.virtualledger.server.banking.api.BankingApiUrlProvider;
import de.fau.amos.virtualledger.server.banking.model.CreateUserBankingModel;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Created by Georg on 18.05.2017.
 */
@RequestScoped @Default
public class HttpUserEndpoint implements UserEndpoint {

    @Inject
    BankingApiUrlProvider urlProvider;


    @Override
    public void createUser(String userId) {

        /*CreateUserBankingModel postBody = new CreateUserBankingModel();
        postBody.setId(userId);

        // Create Jersey client
        Client client = Client.create();

        String url = urlProvider.getUserEndpointUrl();
        WebResource webResourcePOST = client.resource(url);
        webResourcePOST.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON);
        ClientResponse response = webResourcePOST.post(ClientResponse.class, postBody);

        if (response.getStatus() != 201) {
            throw new WebApplicationException("No connection to Adorsys Server!");
        }*/
    }

}
