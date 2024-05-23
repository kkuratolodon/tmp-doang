package com.heymart.authenticate.service;

import com.heymart.authenticate.model.User;
import com.heymart.authenticate.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository<User> userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUsername_UserExists() {
        User mockUser = new User(1L, "John", "Doe", "secure123", "testUser", "ADMIN", true);


        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));

        User user = userService.findByUsername("testUser");

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("testUser");
    }

    @Test
    public void testFindByUsername_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findByUsername("nonexistentUser"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }
}
