package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class AccountRepositoryMockTest {

    private static AccountRepository repository;

    @BeforeClass
    public static void setupClass() {
        repository = new AccountRepositoryCacheDecorator(
                new AccountRepositoryMock(),
                new Cache<>()
        );
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
        assertTrue(repository.findAll().size() == 0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdEx() throws Exception {
        repository.findById(1234567890123L);
    }

    @Test
    public void save() throws Exception {
        Account account = new AccountBuilder()
                .setId(11L)
                .setType("type")
                .setAmountOfMoney(170.0)
                .setCreationDate(new Date())
                .setCNPClient(1234567890123L)
                .build();

        assertTrue(repository.save(account));
    }

}
