package com.webshop.Controller;

import com.webshop.Model.Dto.UserDTO;
import com.webshop.Model.User;
import com.webshop.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Endpoints for managing users")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    @Operation(summary = "get all users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    @Operation(summary = "Get single user")
    public User getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }
    @PostMapping("/users")
    @Operation(summary = "Add new user")
    public User addUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }
}
