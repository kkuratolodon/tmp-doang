package com.heymart.authenticate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterManagerRequest extends RegisterCustomerRequest {
    private String supermarketName;

    public RegisterManagerRequest(String firstName, String lastName, String username, String password, String supermarketName) {
        super(firstName, lastName, username, password);
        this.supermarketName = supermarketName;
    }
}
