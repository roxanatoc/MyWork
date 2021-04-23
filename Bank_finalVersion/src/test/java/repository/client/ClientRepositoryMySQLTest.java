package repository.client;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        clientRepository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                ),
                new Cache<>()
        );
    }

    @Before
    public void cleanUp() {
        clientRepository.removeAll();
    }

    @Test
    public void findAll() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 0);
    }

    @Test
    public void findAllWhenDbNotEmpty() throws Exception {
        Client client = new ClientBuilder()
                .setCNP(1234567890123L)
                .setName("name")
                .setCardNumber(45678)
                .setAddress("address")
                .build();
        clientRepository.save(client);
        clientRepository.save(client);
        clientRepository.save(client);

        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 3);
    }

    @Test
    public void findByCNP() throws Exception {

    }

    @Test
    public void save() throws Exception {
        assertTrue(clientRepository.save(
                new ClientBuilder()
                        .setCNP(1234567890123L)
                        .setName("name")
                        .setCardNumber(45678)
                        .setAddress("address")
                        .build()
        ));
    }

    @Test
    public void removeAll() throws Exception {

    }

}
