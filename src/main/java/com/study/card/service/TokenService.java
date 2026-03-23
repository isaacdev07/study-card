package com.study.card.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.study.card.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // chave secreta temporaria para testes
    @Value("${api.security.token.secret:minha_chave_super_secreta_123}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("card-api") // emissor do token
                    .withSubject(user.getEmail()) // dono do token
                    .withExpiresAt(genExpirationDate()) // tempo util do token
                    .sign(algorithm); 
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant genExpirationDate() {
        // token expira em 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("card-api")
                    .build()
                    .verify(token)
                    .getSubject(); 
        } catch (JWTVerificationException exception) {
            return ""; 
        }
} 

}
