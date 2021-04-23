package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;

import static org.junit.Assert.assertTrue;

public class ClientRepositoryMockTest {

    private static ClientRepository repository;

    @BeforeClass
    public static void setupClass() {
        repository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMock(),
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
    public void findByCNPEx() throws Exception {
        repository.findByCNP(1234567890123L);
    }

    @Test
    public void save() throws Exception {
        Client client = new ClientBuilder()
                .setCNP(1234567890123L)
                .setName("name")
                .setCardNumber(45678)
                .setAddress("address")
                .build();

        assertTrue(repository.save(client));
    }

}
