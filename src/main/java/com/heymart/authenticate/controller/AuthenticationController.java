package com.heymart.authenticate.controller;

import com.heymart.authenticate.dto.*;
import com.heymart.authenticate.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import com.heymart.authenticate.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    JwtService jwtService;

    @Autowired
    private final AuthenticationService authenticationService;

    @Autowired
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterCustomerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('auth:create_manager')")
    public ResponseEntity<AuthenticationResponse> registerManager(
            @RequestBody RegisterManagerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerManager(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(
            HttpServletRequest request, @RequestBody VerifyRequest action
    ) {
        if (action == null || action.getAction() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Action must not be null.");
        }

        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided.");
            }
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token does not contain a valid username.");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!jwtService.isTokenValid(token, userDetails)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
            }

            List<String> authorities = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            if (authorities.contains(action.getAction())) {
                return ResponseEntity.ok().body("Authorized");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error validating token: " + e.getMessage());
        }
    }

}

