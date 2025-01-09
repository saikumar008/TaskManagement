package com.project.taskmanagement.Repository;

import com.project.taskmanagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email); // Additional method to find a user by email

    Optional<User> findByName(String username);
}
