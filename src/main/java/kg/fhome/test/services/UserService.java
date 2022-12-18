package kg.fhome.test.services;


import kg.fhome.test.entity.ConfirmationToken;
import kg.fhome.test.entity.Item;
import kg.fhome.test.entity.User;
import kg.fhome.test.repository.ConfirmationTokenRepository;
import kg.fhome.test.repository.ItemRepository;
import kg.fhome.test.repository.UserRepository;
import kg.fhome.test.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Item> getUserItems(Long id) {
        return itemRepository.findByUserId(id);
    }


}
