package com.modulo2.auth.configurations.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JWTProvider {

    @Value("${login-service.key}")
    private String secret;

    public String createToken(String sub, Date expires) throws Exception{
        try {
            JWSSigner signer = new MACSigner(secret);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
            .subject(sub)
            .expirationTime(expires)
            .claim("permissions", new Permission(false, true))
            .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (Exception e){
            throw new Exception("Erro ao criar token jwt");
        }
    }
}
