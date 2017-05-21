package de.fau.amos.virtualledger.server.banking.api.bankAccountEndpoint;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import de.fau.amos.virtualledger.server.banking.api.BankingApiUrlProvider;
import de.fau.amos.virtualledger.server.banking.api.json.BankAccountJSONBankingModel;
import de.fau.amos.virtualledger.server.banking.model.BankAccountBalanceBankingModel;
import de.fau.amos.virtualledger.server.banking.model.BankAccountBankingModel;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Georg on 18.05.2017.
 */
@RequestScoped @Default
public class HttpBankAccountEndpoint implements BankAccountEndpoint {

    @Inject
    BankingApiUrlProvider urlProvider;


    @Override
    public List<BankAccountBankingModel> getBankAccounts(String userId, String bankingAccessId) {

        // Create Jersey client
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        String url = urlProvider.getBankAccountEndpointUrl(userId, bankingAccessId);
        WebResource.Builder webResourceGET = client.resource(url)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON);
        ClientResponse response = webResourceGET.get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new WebApplicationException("No connection to Adorsys Server!");
        }
        BankAccountJSONBankingModel reponseModel = response.getEntity(BankAccountJSONBankingModel.class);
        if(reponseModel == null || reponseModel.get_embedded() == null)
        { // no accounts found!
            return new ArrayList<BankAccountBankingModel>();
        }
        List<BankAccountBankingModel> result = reponseModel.get_embedded().getBankAccountEntityList();
        return result;
    }
}