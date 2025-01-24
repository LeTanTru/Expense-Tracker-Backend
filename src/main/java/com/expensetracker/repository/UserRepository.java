package com.expensetracker.repository;

import com.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String id);

    boolean existsByEmail(String id);

    boolean existsByPhoneNumber(String id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
