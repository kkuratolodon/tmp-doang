package com.heymart.authenticate.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
@Getter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    protected Admin() { super(); }
    @Builder
    public Admin(Long id, String firstName, String lastName,
                   String password, String username, String role,
                   boolean active) {
        super(id,firstName,lastName,password,username,role,active);
    }
}
