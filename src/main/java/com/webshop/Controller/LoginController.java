package com.webshop.Controller;

import com.webshop.Model.User;
import com.webshop.Service.LoginService;
import com.webshop.Service.User.CustomUserDetails;
import com.webshop.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/profile")
    String home(Model model, Authentication authentication) {
        loginService.getAuthUser(model, authentication);
        return "profile";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute("user") User user, Model model) {
        if (userService.isNotPresent(user)) {
            userService.addNewUser(user, model);
            return ResponseEntity.ok("Registration successful!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }


}
