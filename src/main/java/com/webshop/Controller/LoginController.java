package com.webshop.Controller;

import com.webshop.Service.LoginService;
import com.webshop.Service.User.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    String login() {
        return "login";
    }
    @GetMapping("/profile")
    String home(Model model, Authentication authentication) {
        loginService.getAuthUser(model, authentication);
        return "profile";
    }


}
