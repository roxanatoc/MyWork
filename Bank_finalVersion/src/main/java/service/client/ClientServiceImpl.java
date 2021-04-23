package service.client;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.sql.SQLException;
import java.util.List;

public class ClientServiceImpl implements ClientService{

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }


    @Override
    public Client findByCNP(Long CNP) throws EntityNotFoundException {
        return repository.findByCNP(CNP);
    }

    @Override
    public boolean update(Client client) throws EntityNotFoundException {
        return repository.update(client);
    }

    @Override
    public void removeByCNP(Long CNP) throws EntityNotFoundException {
        repository.removeByCNP(CNP);
    }

    @Override
    public Notification<Boolean> save(Client client){
        ClientValidator clientValidator = new ClientValidator(client);
        Notification<Boolean> clientRegisterNotification = new Notification<>();
        if (!clientValidator.validate()) {
            clientValidator.getErrors().forEach(clientRegisterNotification::addError);
            clientRegisterNotification.setResult(Boolean.FALSE);
        }
        else {
            repository.save(client);
        }
        return clientRegisterNotification;
    }
}
