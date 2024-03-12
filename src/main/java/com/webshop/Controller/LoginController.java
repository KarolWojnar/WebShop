package com.webshop.Controller;

import com.webshop.Model.User;
import com.webshop.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/profile")
    String home(Model model) {
        userService.getAuthUser(model);
        return "profile";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (userService.isNotPresent(user)) {
            String response = userService.addNewUser(user);
            model.addAttribute("successRegister", response);
        }
        else model.addAttribute("errorRegister", "User already exist!");
        return "login";
    }
}
