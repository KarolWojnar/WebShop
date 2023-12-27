package com.webshop.Controller;

import com.webshop.Model.User;
import com.webshop.Service.CustomUserDetails;
import com.webshop.Service.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {

    @GetMapping("/login")
    String login() {
        return "login";
    }
    @GetMapping("/home")
    String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails user) {
                model.addAttribute("user", user.getUser());
            } else {
                model.addAttribute("noUser", "Principal is not recognized as User, UserDetails, or String, it's of type: " + principal.getClass().getName());
            }
        } else {
            model.addAttribute("noUser", "No authenticated User");
        }
        return "home";
    }


}
