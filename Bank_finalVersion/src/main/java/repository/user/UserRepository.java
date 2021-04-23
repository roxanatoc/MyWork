package repository.user;

import model.Client;
import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException;

    boolean save(User user);

    User findById(Long id) throws EntityNotFoundException;

    boolean update(Long id, String username, String password) throws EntityNotFoundException;

    void removeById(Long id) throws EntityNotFoundException;

    void removeAll();

}
