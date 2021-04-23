package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Client findByCNP(Long CNP) throws EntityNotFoundException;

    boolean save(Client client);

    boolean update(Client client) throws EntityNotFoundException;

    void removeByCNP(Long CNP) throws EntityNotFoundException;

    void removeAll();
}
