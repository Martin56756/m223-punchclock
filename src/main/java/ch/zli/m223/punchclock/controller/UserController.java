package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Adds a new user login to the database
     * @param user The login data to add
     */
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody User user) {
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        userRepository.save(user);
    }

    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("userName") String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Gets all users from the database
     * @return All users in the database
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Deletes the user with the specified username from the database
     * @param userName The username of the user to delete
     */
    @DeleteMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("userName") String userName) {
        var user = userRepository.findByUserName(userName);
        userRepository.delete(user);
    }

    /**
     * Updates the specified user in the database
     * @param user The user to update
     * @return The updated user
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
}
