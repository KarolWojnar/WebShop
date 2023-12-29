package com.webshop.Service.User;

import com.webshop.Model.Dto.UserDTO;
import com.webshop.Model.Role;
import com.webshop.Model.User;
import com.webshop.Repository.RoleRepository;
import com.webshop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public User addNewUser(User u, Model model) {
        Role role = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Default role not found"));
        User newUser = new User();
        newUser.setRoles(Collections.singleton(role));
        newUser.setEmail(u.getEmail());
        newUser.setUsername(u.getUsername());
        newUser.setPassword(passwordEncoder.encode(u.getPassword()));
        userRepository.save(newUser);
        model.addAttribute("success", "Registration successfull!");
        model.addAttribute("user", newUser);
        return newUser;
    }

    @Transactional
    public void editUser(String username, String email, Model model, Authentication auth) {
        if (auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof CustomUserDetails user) {
                Optional<User> optionalUser = userRepository.findById(user.getUser().getUserId());
                if (optionalUser.isPresent()) {
                    User editUser = optionalUser.get();
                    editUser.setUsername(username);
                    editUser.setEmail(email);
                    userRepository.save(editUser);
                    model.addAttribute("user", editUser);
                    model.addAttribute("success", "Changes saved successfully.");
                } else model.addAttribute("error", "No user by id");
            } else model.addAttribute("error", "User is non principal of UserDetalis");
        } else model.addAttribute("error", "Error while saving changes.2");
    }

    public boolean isNotPresent(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        return optionalUser.isEmpty();
    }
}
