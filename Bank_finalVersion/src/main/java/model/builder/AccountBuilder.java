package model.builder;

import model.Account;

import java.util.Date;

public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmountOfMoney(double amountOfMoney) {
        account.setAmountOfMoney(amountOfMoney);
        return this;
    }

    public AccountBuilder setCreationDate(Date creationDate) {
        account.setCreationDate(creationDate);
        return this;
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setCNPClient(Long CNPClient) {
        account.setCNPClient(CNPClient);
        return this;
    }

    public Account build() {
        return account;
    }
}
