package com.webshop.Service;

import com.webshop.Service.User.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class LoginService {
    public void getAuthUser(Model model, Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof CustomUserDetails user) {
                model.addAttribute("user", user.getUser());
                model.addAttribute("userEncryptedPasswd", user.getUser().getPassword());
            } else {
                model.addAttribute("noUser", "Principal is not recognized as User, UserDetails, or String, it's of type: " + principal.getClass().getName());
            }
        } else {
            model.addAttribute("noUser", "No authenticated User");
        }
    }
}
