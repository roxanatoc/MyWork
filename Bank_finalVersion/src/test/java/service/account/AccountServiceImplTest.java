package service.account;

import model.Account;
import org.junit.Before;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMock;
import service.account.AccountService;
import service.account.AccountServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountServiceImplTest {

    private AccountService accountService;

    @Before
    public void setup() {
        accountService = new AccountServiceImpl(new AccountRepositoryMock());
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(0, accountService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdEx() throws Exception {
        accountService.findById(11L);
    }

    @Test
    public void save() throws Exception {
        assertTrue(accountService.save(new Account()));
    }
}
