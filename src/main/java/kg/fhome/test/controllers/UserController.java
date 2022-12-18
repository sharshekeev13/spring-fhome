package kg.fhome.test.controllers;


import jakarta.validation.Valid;
import kg.fhome.test.entity.Item;
import kg.fhome.test.entity.User;
import kg.fhome.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public User saveUser(@Valid User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/items")
    public List<Item> getUserItems(@PathVariable("id") Long id){
        return userService.getUserItems(id);
    }

}
