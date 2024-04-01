package com.orion.mdd.controllers;

import com.orion.mdd.payloads.authentification.RegisterRequestDto;
import com.orion.mdd.models.User;
import com.orion.mdd.payloads.api.ApiResponse;
import com.orion.mdd.payloads.authentification.LoginRequestDto;
import com.orion.mdd.payloads.authentification.TokenDto;
import com.orion.mdd.services.UserService;
import com.orion.mdd.services.configurations.JWTService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "The Authentication API. Contains all the operations that can be performed for authentication.")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    @SecurityRequirements()
    public ResponseEntity<?> getToken(@RequestBody LoginRequestDto loginRequestDto) {
        User user = this.userService.getUser(loginRequestDto.getEmail());

        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Unknown user login."), HttpStatus.UNAUTHORIZED);
        }

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Token requested for user: {}", authentication.getAuthorities());

            String token = jwtService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDto(token));
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    @SecurityRequirements()
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        if(this.userService.existsByEmail(registerRequestDto.getEmail())) {
            return new ResponseEntity<>(new ApiResponse("Error: Email address already used."), HttpStatus.BAD_REQUEST);
        }

        this.userService.createUser(registerRequestDto.getEmail(), registerRequestDto.getUsername(), registerRequestDto.getPassword());

        return ResponseEntity.ok(new ApiResponse("User registered successfully!"));
    }
}
