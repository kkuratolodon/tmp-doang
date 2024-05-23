package com.heymart.authenticate.service;

import com.heymart.authenticate.model.User;

public interface UserService {
    public User findByUsername(String username);
}
