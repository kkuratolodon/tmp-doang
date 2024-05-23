package com.heymart.authenticate.repository;

import com.heymart.authenticate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository <T extends User> extends JpaRepository<T, Long> {
    Optional<T> findByUsername(String username);
}
