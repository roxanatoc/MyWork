package service.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.user.AuthenticationException;

import java.util.List;

public interface UserService {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException;

    User findById(Long id) throws EntityNotFoundException;

    boolean update(Long id, String username, String password) throws EntityNotFoundException;

    void removeById(Long id) throws EntityNotFoundException;

    boolean save(User user);


}
