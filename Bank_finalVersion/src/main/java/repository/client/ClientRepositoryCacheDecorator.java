package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator{

    private Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository clientRepository, Cache<Client> cache) {
        super(clientRepository);
        this.cache = cache;
    }

    @Override
    public List<Client> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Client> clients = decoratedRepository.findAll();
        cache.save(clients);
        return clients;
    }

    @Override
    public Client findByCNP(Long CNP) throws EntityNotFoundException {
        return decoratedRepository.findByCNP(CNP);
    }

    @Override
    public boolean save(Client client) {
        cache.invalidateCache();
        return decoratedRepository.save(client);
    }

    @Override
    public boolean update(Client client) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.update(client);
    }

    @Override
    public void removeByCNP(Long CNP) throws EntityNotFoundException {
        cache.invalidateCache();
        decoratedRepository.removeByCNP(CNP);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}
