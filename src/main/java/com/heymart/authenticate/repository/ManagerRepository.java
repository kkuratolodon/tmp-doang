package com.heymart.authenticate.repository;

import com.heymart.authenticate.model.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends UserRepository<Manager> {
}
