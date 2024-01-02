package com.servifacil.backendapp.library.util;

import com.servifacil.backendapp.library.security.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JWTProvider jwtProvider;

    public AuthResponse login(AuthCredentials credentials) {
        try {
            // Decodificar la contraseña Base64
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(credentials.getPassword());
            String passwordDecode = new String(decodedBytes);

            // Autenticar al usuario con Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), passwordDecode)
            );

            // Obtener el usuario desde el repositorio
            User user = userRepository.findByUsernameOrEmail(credentials.getEmail(), credentials.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            // Crear la respuesta de autenticación
            UserResponse userResponse = UserResponse.fromUser(user);
            String token = jwtProvider.generateToken(user.getUsername());

            return new AuthResponse(token, userResponse);
        } catch (Exception e) {
            // Manejar la excepción de autenticación
            throw new BadCredentialsException("Credenciales inválidas");
        }
    }

}
