package de.fau.amos.virtualledger.server.banking.api.bankAccountEndpoint;

import de.fau.amos.virtualledger.server.banking.api.BankingApiDummy;
import de.fau.amos.virtualledger.server.banking.model.BankAccessBankingModel;
import de.fau.amos.virtualledger.server.banking.model.BankAccountBalanceBankingModel;
import de.fau.amos.virtualledger.server.banking.model.BankAccountBankingModel;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Georg on 18.05.2017.
 */
@RequestScoped @BankingApiDummy
public class DummyBankAccountEndpoint implements BankAccountEndpoint {

    @Override
    public List<BankAccountBankingModel> getBankAccounts(String userId, String bankingAccessId) {
        List<BankAccountBankingModel> dummyData = new ArrayList<BankAccountBankingModel>();
        dummyData.add(this.generateDummyBankAccountModel(1, bankingAccessId));
        dummyData.add(this.generateDummyBankAccountModel(2, bankingAccessId));
        return dummyData;

    }

    /**
     * generates a BankAccountBankingModel with dummy data
     * @param id that makes multiple dummies different via a postfix in every attribute
     * @return
     */
    private BankAccountBankingModel generateDummyBankAccountModel(int id, String bankingAccessId)
    {
        BankAccountBankingModel bankAccountBankingModel = new BankAccountBankingModel();
        BankAccountBalanceBankingModel bankAccountBalanceBankingModel = new BankAccountBalanceBankingModel();
        bankAccountBalanceBankingModel.setAvailableHbciBalance(510.20);

        bankAccountBankingModel.setBankAccountBalance(bankAccountBalanceBankingModel);
        bankAccountBankingModel.setCountryHbciAccount("DE");
        bankAccountBankingModel.setBlzHbciAccount("DummyBLZ " + id);
        bankAccountBankingModel.setNumberHbciAccount("DummyHbciAccountNummer " + id);
        bankAccountBankingModel.setTypeHbciAccount("Dummy Tilgungscredit " + id);
        bankAccountBankingModel.setCurrencyHbciAccount("EUR");
        bankAccountBankingModel.setNameHbciAccount("Dummy Adam");
        bankAccountBankingModel.setBicHbciAccount("DummyBIC " + id);
        bankAccountBankingModel.setIbanHbciAccount("DummyIBAN " + id);
        bankAccountBankingModel.setId("Dummy ID " + id);
        bankAccountBankingModel.setBankAccessId(bankingAccessId);

        return bankAccountBankingModel;
    }
}