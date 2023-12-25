package com.webshop.Repository;

import com.webshop.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsernameOrEmail(String username, String email);
}
