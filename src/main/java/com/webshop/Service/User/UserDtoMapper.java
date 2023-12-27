package com.webshop.Service.User;

import com.webshop.Model.Dto.UserDTO;
import com.webshop.Model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {
    public UserDtoMapper() {
    }

    public static List<UserDTO> mapToUsersDtos(List<User> allUsers) {
        return allUsers.stream()
                .map(user -> mapToUserDtos(user)).collect(Collectors.toList());
    }

    private static UserDTO mapToUserDtos(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
