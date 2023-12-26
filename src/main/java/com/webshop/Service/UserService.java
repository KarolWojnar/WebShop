package com.webshop.Service;

import com.webshop.Model.Dto.UserDTO;
import com.webshop.Model.Role;
import com.webshop.Model.User;
import com.webshop.Repository.RoleRepository;
import com.webshop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findUsers();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public User addNewUser(User u) {
        Role role = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Default role not found"));
        u.setRoles(Collections.singleton(role));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userRepository.save(u);
        return u;
    }
}
