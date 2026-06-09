package com.JaehJunior.ecommercebackend.controller;

import com.JaehJunior.ecommercebackend.dto.AuthRequest;
import com.JaehJunior.ecommercebackend.dto.AuthResponse;
import com.JaehJunior.ecommercebackend.entity.User;
import com.JaehJunior.ecommercebackend.repository.UserRepository;
import com.JaehJunior.ecommercebackend.security.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(
            UserRepository userRepository,
            JwtService jwtService,
            BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        System.out.println("========== LOGIN ENDPOINT HIT ==========");
        System.out.println("EMAIL RECEIVED: " + request.getEmail());
        System.out.println("PASSWORD RECEIVED: " + request.getPassword());

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            System.out.println("USER NOT FOUND");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email");
        }

        System.out.println("USER FOUND");
        System.out.println("PASSWORD IN DB: " + user.getPassword());

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        System.out.println("PASSWORD MATCHES: " + matches);

        if (!matches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }

        String token =
        jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );

        System.out.println("TOKEN GENERATED SUCCESSFULLY");

        return ResponseEntity.ok(new AuthResponse(token));
    }
}