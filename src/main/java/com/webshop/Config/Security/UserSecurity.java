package com.webshop.Config.Security;

import com.webshop.Service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {

    public boolean checkUserId(Authentication authentication, Long userId) {
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUser().getUserId().equals(userId);
        }
        return false;
    }
}
