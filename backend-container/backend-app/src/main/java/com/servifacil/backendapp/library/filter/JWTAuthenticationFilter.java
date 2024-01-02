package com.servifacil.backendapp.library.filter;

import com.servifacil.backendapp.library.security.JWTProvider;
import com.servifacil.backendapp.library.security.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromRequest(request);

        if (!StringUtils.hasText(token))
        {
            filterChain.doFilter(request, response);
            return;
        }

        final  String username = jwtProvider.getUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            final UserDetails userDetails = userRepository.findByUsernameOrEmail(username,username).orElseThrow();

           if (jwtProvider.validateToken(token, userDetails)){
               final UsernamePasswordAuthenticationToken authenticationToken =
                       new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
               authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }

        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")){
            return header.substring(7);
        }

        return null;
    }
}
