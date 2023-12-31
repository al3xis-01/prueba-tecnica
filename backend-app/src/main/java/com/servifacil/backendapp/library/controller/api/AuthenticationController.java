package com.servifacil.backendapp.library.controller.api;


import com.servifacil.backendapp.library.security.AuthCredentials;
import com.servifacil.backendapp.library.security.AuthResponse;
import com.servifacil.backendapp.library.util.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Authentication")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/Login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthCredentials credentials){
        AuthResponse response = authenticationService
                .login(credentials);
        return ResponseEntity.ok(response);
    }

}
