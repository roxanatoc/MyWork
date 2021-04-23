package repository.account;

import database.DBConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountRepositoryMySQLTest {
    private static AccountRepository accountRepository;

    @BeforeClass
    public static void setupClass() {
        accountRepository = new AccountRepositoryCacheDecorator(
                new AccountRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                ),
                new Cache<>()
        );
    }

    @Before
    public void cleanUp() {
        accountRepository.removeAll();
    }

    @Test
    public void findAll() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void findAllWhenDbNotEmpty() throws Exception {
        Account account = new AccountBuilder()
                .setId(11L)
                .setType("type")
                .setAmountOfMoney(170.0)
                .setCreationDate(new Date())
                .setCNPClient(1234567890123L)
                .build();
        accountRepository.save(account);
        accountRepository.save(account);
        accountRepository.save(account);

        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 3);
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void save() throws Exception {
        assertTrue(accountRepository.save(
                new AccountBuilder()
                        .setId(11L)
                        .setType("type")
                        .setAmountOfMoney(170.0)
                        .setCreationDate(new Date())
                        .setCNPClient(1234567890123L)
                        .build()
        ));
    }

    @Test
    public void removeAll() throws Exception {

    }

}
