package de.fau.amos.virtualledger.dtos;

/**
 * Created by Simon on 20.05.2017.
 */

public class BankAccount {

    private String bankid;
    private String name;
    private double balance;

    /**
     *
     * @methodtype constructor
     */
    public BankAccount() {
    }

    /**
     *
     * @param bankid
     * @param name
     * @param balance
     * @methodtype constructor
     */
    public BankAccount(String bankid, String name, double balance) {
        this.bankid = bankid;
        this.name = name;
        this.balance = balance;
    }

    /**
     *
     * @return bankid
     * @methodtype getter
     */
    public String getBankid() {
        return bankid;
    }

    /**
     *
     * @param bankid
     * @methodtype setter
     */
    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    /**
     *
     * @return name of bank account
     * @methodtype getter
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * @methodtype setter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return balance
     * @methodtype getter
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     * @methodtype setter
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
