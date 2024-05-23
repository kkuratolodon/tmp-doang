package com.heymart.authenticate.repository;

import com.heymart.authenticate.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}
