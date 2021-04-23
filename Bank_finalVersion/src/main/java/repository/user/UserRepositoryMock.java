package repository.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryMock implements  UserRepository{

    private List<User> users;

    public UserRepositoryMock() {
        users = new ArrayList<>();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean save(User user) {
        return users.add(user);
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        List<User> filteredUsers = users.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredUsers.size() > 0) {
            return filteredUsers.get(0);
        }
        throw new EntityNotFoundException(id, User.class.getSimpleName());
    }

    @Override
    public boolean update(Long id, String username, String password) throws EntityNotFoundException {
        users.get(Integer.parseInt("" + id)).setUsername(username);
        users.get(Integer.parseInt("" + id)).setPassword(password);
        return true;
    }

    @Override
    public void removeById(Long id) throws EntityNotFoundException {
        users.remove(this.findById(id));
    }

    @Override
    public void removeAll() {
        users.clear();
    }
}
