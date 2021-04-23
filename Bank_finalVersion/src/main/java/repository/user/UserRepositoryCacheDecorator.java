package repository.user;


import model.User;
import model.validation.Notification;
import repository.Cache;
import repository.EntityNotFoundException;
import java.util.List;

public class UserRepositoryCacheDecorator extends UserRepositoryDecorator{

    private Cache<User> cache;

    public UserRepositoryCacheDecorator(UserRepository userRepository, Cache<User> cache) {
        super(userRepository);
        this.cache = cache;
    }

    @Override
    public List<User> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<User> users = decoratedRepository.findAll();
        cache.save(users);
        return users;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean save(User user) {
        cache.invalidateCache();
        return decoratedRepository.save(user);
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean update(Long id, String username, String password) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.update(id, username, password);
    }

    @Override
    public void removeById(Long id) throws EntityNotFoundException {
        cache.invalidateCache();
        decoratedRepository.removeById(id);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}
