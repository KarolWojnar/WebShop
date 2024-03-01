package com.webshop.Repository;

import com.webshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query("Select u from User u left join fetch u.roles")
    List<User> findUsers();

    Optional<User> findUserByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> getUserByUserId(int id);
}
