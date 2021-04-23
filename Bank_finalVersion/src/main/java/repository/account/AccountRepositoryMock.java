package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepositoryMock implements AccountRepository{

    private List<Account> accounts;

    public AccountRepositoryMock() {
        accounts = new ArrayList<>();
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        List<Account> filteredAccounts = accounts.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredAccounts.size() > 0) {
            return filteredAccounts.get(0);
        }
        throw new EntityNotFoundException(id, Account.class.getSimpleName());
    }

    @Override
    public boolean save(Account account) {
        return accounts.add(account);
    }

    @Override
    public void removeById(Long id) throws EntityNotFoundException {
        accounts.remove(this.findById(id));
    }

    @Override
    public boolean update(Account account) throws EntityNotFoundException {
        accounts.remove(this.findById(account.getId()));
        return accounts.add(account);
    }

    @Override
    public void transfer(Long id1, Long id2, double amount) throws EntityNotFoundException {
        accounts.get(Integer.parseInt("" + id1)).setAmountOfMoney(accounts.get(Integer.parseInt("" + id1)).getAmountOfMoney() - amount);
        accounts.get(Integer.parseInt("" + id2)).setAmountOfMoney(accounts.get(Integer.parseInt("" + id2)).getAmountOfMoney() + amount);
    }

    @Override
    public void processBill(Long id, double amount) {
        accounts.get(Integer.parseInt("" + id)).setAmountOfMoney(accounts.get(Integer.parseInt("" + id)).getAmountOfMoney() - amount);
    }

    @Override
    public void removeAll() {
        accounts.clear();
    }
}
