package repository.account;

import model.Account;
import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class AccountRepositoryCacheDecorator extends AccountRepositoryDecorator{

    private Cache<Account> cache;

    public AccountRepositoryCacheDecorator(AccountRepository accountRepository, Cache<Account> cache) {
        super(accountRepository);
        this.cache = cache;
    }

    @Override
    public List<Account> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Account> accounts = decoratedRepository.findAll();
        cache.save(accounts);
        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(Account account) {
        cache.invalidateCache();
        return decoratedRepository.save(account);
    }

    @Override
    public void removeById(Long id) throws EntityNotFoundException {
        cache.invalidateCache();
        decoratedRepository.removeById(id);
    }

    @Override
    public boolean update(Account account) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.update(account);
    }

    @Override
    public void transfer(Long id1, Long id2, double amount) throws EntityNotFoundException {
        cache.invalidateCache();
        decoratedRepository.transfer(id1, id2, amount);
    }

    @Override
    public void processBill(Long id, double amount) {
        cache.invalidateCache();
        decoratedRepository.processBill(id, amount);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}
