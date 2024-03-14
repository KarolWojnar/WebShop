package com.webshop.Service.User;

import com.webshop.Model.Role;
import com.webshop.Model.User;
import com.webshop.Repository.RoleRepository;
import com.webshop.Repository.UserRepository;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public User getAuthUser(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            User user = userDetails.getUser();
            model.addAttribute("user", user);
            return user;
        }
        return null;
    }
    public User getAuthUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            User user = userDetails.getUser();
            return user;
        }
        return null;
    }
    @Transactional
    public String addNewUser(User u) {
        Role role = roleRepository.findById(2L).orElseThrow();
        User newUser = new User();
        newUser.setRoles(Collections.singleton(role));
        newUser.setEmail(u.getEmail());
        newUser.setActivated(false);
        newUser.setUsername(u.getUsername());
        newUser.setPassword(passwordEncoder.encode(u.getPassword()));
        String activationToken = generateActivationToken();
        newUser.setActivationToken(activationToken);
        userRepository.save(newUser);
        return "Registration Succesfull!";
    }

    @Transactional
    public void editUser(String username, String email, Model model, Authentication auth) {
        User user = getAuthUser(model);
        if (user != null) {
            Optional<User> optionalUser = userRepository.findById(user.getUserId());
            if (optionalUser.isPresent()) {
                User editUser = optionalUser.get();
                editUser.setUsername(username);
                editUser.setEmail(email);
                userRepository.save(editUser);
                model.addAttribute("user", editUser);
                model.addAttribute("success", "Changes saved successfully.");
            } else {
                model.addAttribute("error", "No user found");
            }
        } else {
            model.addAttribute("error", "User not authenticated");
        }
    }

    public boolean isNotPresent(User user) {
        Optional<User> optionalUserEmail = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        return (optionalUserEmail.isEmpty());
    }

    private String generateActivationToken() {
        return UUID.randomUUID().toString();
    }

    public String activateAccount(Long userId, String token, Model model) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            if (user.getActivationToken() != null && user.getActivationToken().equals(token)) {
                user.setActivated(true);
                user.setActivationToken(null);
                userRepository.save(user);
                return "Account activated successfully!";
            }
        }
       return "User not found";
    }

    @Transactional
    public void changePassword(Model model, Authentication auth, String newPass, String oldPass) {
        if (auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof CustomUserDetails user) {
                Optional<User> optional = userRepository.getUserByUserId(Math.toIntExact(user.getUser().getUserId()));
                if (optional.isPresent()) {
                    User userPassword = optional.get();
                    model.addAttribute("user", userPassword);
                    if (passwordEncoder.matches(oldPass, userPassword.getPassword())) {
                        userPassword.setPassword(passwordEncoder.encode(newPass));
                        userRepository.save(userPassword);
                        model.addAttribute("result", "Password changed!");
                    } else model.addAttribute("result", "previous password is wrong!");
                }
            }
        }
    }
    @Transactional
    public boolean deleteUserById(int id) {
        try {
            userRepository.deleteById((long) id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
