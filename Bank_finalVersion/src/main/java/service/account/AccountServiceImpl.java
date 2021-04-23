package service.account;

import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService{

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return repository.findById(id);
    }

    @Override
    public void removeById(Long id) throws EntityNotFoundException {
        repository.removeById(id);
    }

    @Override
    public boolean update(Account account) throws EntityNotFoundException {
        return repository.update(account);
    }

    @Override
    public void transfer(Long id1, Long id2, double amount) throws EntityNotFoundException {
        repository.transfer(id1, id2, amount);
    }

    @Override
    public void processBill(Long id, double amount) {
        repository.processBill(id, amount);
    }

    @Override
    public boolean save(Account account) {
        return repository.save(account);
    }
}
