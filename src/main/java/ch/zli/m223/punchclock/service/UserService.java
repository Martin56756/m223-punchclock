package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {
    private UserRepository applicationUserRepository;

    public UserService(UserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ch.zli.m223.punchclock.domain.User applicationUser = applicationUserRepository.findByUserName(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUserName(), applicationUser.getPassWord(), emptyList());
    }
}
