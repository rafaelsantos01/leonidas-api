package com.spantanTech.leonidas.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spantanTech.leonidas.modules.users.entities.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(Users user){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
        .withIssuer("leonidas-api")
        .withSubject(user.getUserMail())
        .withExpiresAt(genExpirationDateAccessToken())
        .sign(algorithm);

    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while generating token", exception);
    }
  }


  public String generateRefreshToken(Users user){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.create()
              .withSubject(user.getUserMail())
              .withExpiresAt(genExpirationDateRefreshToken())
              .sign(algorithm);

    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while generating token", exception);
    }
  }

  public String validateToken(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
        .withIssuer("leonidas-api")
        .build()
        .verify(token)
        .getSubject();

    } catch (JWTVerificationException exception){
      return "";
    }
  }

  private Instant genExpirationDateAccessToken(){
    return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
  }

  private Instant genExpirationDateRefreshToken(){
    return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
  }
}
