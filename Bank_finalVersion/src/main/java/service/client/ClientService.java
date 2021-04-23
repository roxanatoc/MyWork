package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findByCNP(Long CNP) throws EntityNotFoundException;

    boolean update(Client client) throws EntityNotFoundException;

    void removeByCNP(Long CNP) throws EntityNotFoundException;

    Notification<Boolean> save(Client client);
}
