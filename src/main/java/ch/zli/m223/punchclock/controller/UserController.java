package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
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

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        userRepository.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable("userName") String userName) {
        var user = userRepository.findByUserName(userName);
        userRepository.delete(user);
    }
}
