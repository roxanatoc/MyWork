package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepositoryMock implements ClientRepository{

    private List<Client> clients;

    public ClientRepositoryMock() {
        clients = new ArrayList<>();
    }

    public List<Client> findAll() {
        return clients;
    }

    public Client findByCNP(Long CNP) throws EntityNotFoundException {
        List<Client> filteredClients = clients.parallelStream()
                .filter(it -> it.getCNP().equals(CNP))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
            return filteredClients.get(0);
        }
        throw new EntityNotFoundException(CNP, Client.class.getSimpleName());
    }

    public boolean save(Client client) {
        return clients.add(client);
    }

    @Override
    public boolean update(Client client) throws EntityNotFoundException {
        clients.remove(this.findByCNP(client.getCNP()));
        return clients.add(client);
    }

    @Override
    public void removeByCNP(Long CNP) throws EntityNotFoundException {
        clients.remove(this.findByCNP(CNP));
    }

    @Override
    public void removeAll() {
        clients.clear();
    }
}
