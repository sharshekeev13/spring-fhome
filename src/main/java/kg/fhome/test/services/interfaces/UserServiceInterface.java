package kg.fhome.test.services.interfaces;

import kg.fhome.test.entity.Item;
import kg.fhome.test.entity.User;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    User saveUser(User user);
    void deleteUser(Long id);
    List<Item> getUserItems(Long id);
}
