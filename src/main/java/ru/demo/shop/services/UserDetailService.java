package ru.demo.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.demo.shop.models.User;
import ru.demo.shop.repositories.UsersRepository;
import ru.demo.shop.security.UserDetails;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User with username " + username + " not found.");
        return new UserDetails(user.get());
    }
}
