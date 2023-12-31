package com.webshop.Controller;

import com.webshop.Model.User;
import com.webshop.Service.LoginService;
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
@RequestMapping("/profile")
public class UserController {
    private final UserService userService;
    private final LoginService loginService;
    @GetMapping("/edit")
    public String userEditProfile(Model model, Authentication auth) {
        loginService.getAuthUser(model, auth);
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String editUser(@RequestParam("newUsername") String username, @RequestParam("newEmail") String email,
                             Model model, Authentication auth) {
        userService.editUser(username, email, model, auth);
        return "editProfile";
    }

    @GetMapping("/activate/{userId}?token={token}")
    public ResponseEntity<String> activateAccount(@PathVariable Long userId, @PathVariable String token) {
        return userService.activateAccount(userId, token);
    }
}
