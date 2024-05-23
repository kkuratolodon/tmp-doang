package com.heymart.authenticate.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
    protected Customer() { super(); }

    @Builder
    public Customer(Long id, String firstName, String lastName,
                    String password, String username, String role,
                    boolean active) {
        super(id,firstName,lastName,password,username,role,active);
    }
}
