package com.heymart.authenticate.service.auth;

import com.heymart.authenticate.dto.AuthenticationRequest;
import com.heymart.authenticate.dto.AuthenticationResponse;
import com.heymart.authenticate.dto.RegisterCustomerRequest;
import com.heymart.authenticate.dto.RegisterManagerRequest;
import com.heymart.authenticate.exception.UserAlreadyExistException;
import com.heymart.authenticate.model.Customer;
import com.heymart.authenticate.model.Manager;
import com.heymart.authenticate.model.User;
import com.heymart.authenticate.repository.CustomerRepository;
import com.heymart.authenticate.repository.ManagerRepository;
import com.heymart.authenticate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository<User> userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse register(RegisterCustomerRequest request) {
        var checkUser = userRepository.findByUsername(request.getUsername()).orElse(null);

        if(checkUser != null) {
            throw new UserAlreadyExistException();
        }
        Customer user = Customer.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .active(true)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("CUSTOMER")
                .build();

        customerRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse registerManager(RegisterManagerRequest request) {
        var checkUser = userRepository.findByUsername(request.getUsername()).orElse(null);


        if(checkUser != null) {
            throw new UserAlreadyExistException();
        }

        Manager user = Manager.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .active(true)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("MANAGER")
                .supermarketName(request.getSupermarketName())
                .build();

        managerRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }


}


