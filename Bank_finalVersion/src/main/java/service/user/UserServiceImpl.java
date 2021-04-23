package service.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.user.AuthenticationException;
import repository.user.UserRepository;

import java.security.MessageDigest;
import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        return repository.findById(id);
    }

    @Override
    public boolean update(Long id, String username, String password) throws EntityNotFoundException {
        return repository.update(id, username, encodePassword(password));
    }

    @Override
    public void removeById(Long id) throws EntityNotFoundException {
        repository.removeById(id);
    }


    @Override
    public boolean save(User user) {
        return repository.save(user);
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
