package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean save(Account account);

    void removeById(Long id) throws EntityNotFoundException;

    boolean update(Account account) throws EntityNotFoundException;

    void transfer(Long id1, Long id2, double amount) throws EntityNotFoundException;

    void processBill(Long id, double amount);

    void removeAll();
}
