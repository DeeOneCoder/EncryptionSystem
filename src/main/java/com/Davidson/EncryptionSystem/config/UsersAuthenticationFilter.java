package com.Davidson.EncryptionSystem.config;

import com.Davidson.EncryptionSystem.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UsersAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JWTService jwtService;

    private final UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        //String to hold the request header
        final String authenticationHeader = request.getHeader("Authorization");

        //String to hold the jwt token
        final String jwtToken;

        if( authenticationHeader == null || !authenticationHeader.startsWith("Bearer ") ){
            filterChain.doFilter(request, response);
            return;
        }
        //Extract token from header
        jwtToken = authenticationHeader.substring(7);

        //Extract username/email from token
        String username = jwtService.extractUsername(jwtToken);

        //Validate the username
        if( username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            //Load UserDetails using the userDetailsService.loadUserByUsername(email) method
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if( jwtService.isTokenValid(jwtToken, userDetails)){

                //Create a UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //SetDetails on usernamePasswordAuthenticationToken with a WebAuthenticationDetailsSource().buildDetails(HttpServletRequest)
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Set SecurityContextHolder with the credentials
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

            filterChain.doFilter(request, response);

        }






    }
}
