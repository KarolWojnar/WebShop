package com.webshop.Controller;

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
    @GetMapping("/edit")
    public String userEditProfile(Model model, Authentication auth) {
        userService.getAuthUser(model, auth);
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String editUser(@RequestParam("newUsername") String username, @RequestParam("newEmail") String email,
                             Model model, Authentication auth) {
        userService.editUser(username, email, model, auth);
        return "editProfile";
    }

    @GetMapping("/activate/{userId}")
    public String activateAccount(@PathVariable Long userId, @RequestParam String token, Model model) {
        String response = userService.activateAccount(userId, token, model);
        model.addAttribute("activationMessage", response);
        return "profile";
    }
    @PostMapping("/newPassword")
    public String changePassword(Model model, Authentication auth, @RequestParam String newPassword,
                                 @RequestParam String oldPassword) {
        userService.changePassword(model, auth, newPassword, oldPassword);
        return "editProfile";
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        boolean deleted = userService.deleteUserById(userId);
        if (deleted) return ResponseEntity.ok("User deleted!");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
