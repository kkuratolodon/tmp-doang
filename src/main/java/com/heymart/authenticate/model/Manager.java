package com.heymart.authenticate.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
@Getter
@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User{
    @Column(name = "supermarket_name")
    private String supermarketName;

    protected Manager() { super(); }
    @Builder
    public Manager(Long id, String firstName, String lastName,
                    String password, String username, String role,
                    boolean active, String supermarketName) {
        super(id,firstName,lastName,password,username,role,active);
        this.supermarketName = supermarketName;
    }
}
