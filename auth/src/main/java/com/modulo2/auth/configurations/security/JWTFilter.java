package com.modulo2.auth.configurations.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import tools.jackson.databind.ObjectMapper;

@Service
public class JWTFilter implements Filter{

    @Value("${login-service.key}")
    private String secret;

    public List<GrantedAuthority> getPermissions(Object permissions){

        ObjectMapper mapper = new ObjectMapper();
        Permission perm = mapper.convertValue(permissions, Permission.class);

        ArrayList<GrantedAuthority> list = new ArrayList<>();

        if(perm.isUser()) list.add(new SimpleGrantedAuthority("ROLE_USER"));

        return list;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        
        if(req.getHeader("Authorization") != null){
            String[] header = req.getHeader("Authorization").split(" ");
            String token = null;

            if(header.length == 2){
                token = header[1];
            } else {
                chain.doFilter(request, response);
            }
            
            try {
                SignedJWT signedJWT = SignedJWT.parse(token);
                
                JWSVerifier verifier = new MACVerifier(this.secret);

                JWTClaimsSet information = signedJWT.getJWTClaimsSet();

                String subject = information.getSubject();

                if(signedJWT.verify(verifier)){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        User.withUsername(subject).build(), null, null);
                    SecurityContext security = SecurityContextHolder.createEmptyContext();
                    security.setAuthentication(auth);
                    SecurityContextHolder.setContext(security);
                    chain.doFilter(request, response);
                } else {
                    chain.doFilter(request, response);
                }
            } catch (Exception e){
                chain.doFilter(request, response);
            }
        
        } else {
            chain.doFilter(request, response);
        }
    }

    
}
