package com.webshop.Service;

import com.webshop.Model.User;
import com.webshop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User addNewUser(String name, String email, String password) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        u.setUsername(name);
        userRepository.save(u);
        return u;
    }
}
