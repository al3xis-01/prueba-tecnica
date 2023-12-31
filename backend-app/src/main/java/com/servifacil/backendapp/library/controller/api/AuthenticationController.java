package com.servifacil.backendapp.library.controller.api;


import com.servifacil.backendapp.library.security.AuthCredentials;
import com.servifacil.backendapp.library.security.AuthResponse;
import com.servifacil.backendapp.library.util.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Authentication")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @GetMapping("/Login/{email}/{password}")
    public ResponseEntity<AuthResponse> login(@PathVariable String email, @PathVariable String password){
        AuthCredentials credentials = new AuthCredentials(email, password);
        AuthResponse response = authenticationService
                .login(credentials);
        return ResponseEntity.ok(response);
    }

}
