package com.heymart.authenticate.repository;

import com.heymart.authenticate.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {
}
