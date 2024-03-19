package com.webshop.Model.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private String email;
}
