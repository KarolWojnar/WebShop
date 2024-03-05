package com.webshop;

import com.webshop.Model.Role;
import com.webshop.Model.User;
import com.webshop.Repository.RoleRepository;
import com.webshop.Repository.UserRepository;
import com.webshop.Service.User.CustomUserDetails;
import com.webshop.Service.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository, passwordEncoder, roleRepository);
    }

    @Test
    public void testAddNewUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setUsername("testUser");
        user.setPassword("TestPassword");

        Role role = new Role();
        role.setRoleId(2L);
        when(roleRepository.findById(2L)).thenReturn(java.util.Optional.of(role));
        when(passwordEncoder.encode("TestPassword")).thenReturn("encodedpassword");
        String result = userService.addNewUser(user);
        assertEquals("Registration Succesfull!", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testEditUser() {
        String username = "newUsername";
        String email = "newEmail@example.com";
        User user = new User();
        user.setUserId(1L);
        Authentication auth = new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), null);

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        Model model = mock(Model.class);
        userService.editUser(username, email, model, auth);

        verify(userRepository, times(1)).save(user);

        verify(model, times(1)).addAttribute(eq("user"), eq(user));
        verify(model, times(1)).addAttribute(eq("success"), eq("Changes saved successfully."));
    }
}
