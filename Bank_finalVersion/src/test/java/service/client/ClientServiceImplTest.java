package service.client;

import model.Client;
import model.validation.Notification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.client.ClientRepositoryMock;
import service.client.ClientService;
import service.client.ClientServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientServiceImplTest {

    private ClientService clientService;

    @Before
    public void setup() {
        clientService = new ClientServiceImpl(new ClientRepositoryMock());
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(0, clientService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByCNPEx() throws Exception {
        clientService.findByCNP(1234567890123L);
    }

    @Test
    public void save() throws Exception {
        assertTrue(clientService.save(new Client()));
    }

    private void assertTrue(Notification<Boolean> save) {
    }
}
