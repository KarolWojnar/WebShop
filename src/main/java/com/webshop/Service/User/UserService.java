package com.webshop.Service.User;

import com.webshop.Model.Role;
import com.webshop.Model.User;
import com.webshop.Repository.RoleRepository;
import com.webshop.Repository.UserRepository;
import com.webshop.Service.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private final EmailService emailService;

    public List<User> getAllUsers() {
        return userRepository.findUsers();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
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
//        sendActivationEmail(newUser, activationToken);
        userRepository.save(newUser);
        return "Registration Succesfull!";
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

    @Transactional
    public void sendActivationEmail(User user, String activationToken) {
        String activationLink = "http://localhost:8080/activate/" + user.getUserId() + "?token=" + activationToken;
        String emailBody = "Click the following link to activate your account: " + activationLink;
        emailService.sendEmail(user.getEmail(), "Account Activation", emailBody);
    }

    private String generateActivationToken() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public ResponseEntity<String> activateAccount(Long userId, String token) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getActivationToken() != null && user.getActivationToken().equals(token)) {
                user.setActivated(true);
                user.setActivationToken(null);
                userRepository.save(user);
                return ResponseEntity.ok("Account activated successfully!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
