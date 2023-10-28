package com.spantanTech.leonidas.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spantanTech.leonidas.modules.users.entities.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  @Value("${api.security.token.secret}")
  private String secretRefresh;

  public String generateToken(Users user){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("leonidas-api")
                .withSubject(user.getUserMail())
                .withExpiresAt(genExpirationAccessToken())
                .sign(algorithm);

    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while generating token", exception);
    }
  }

  public String generateRefreshToken(Users user){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.create()
              .withIssuer("leonidas-api")
              .withSubject(user.getUserMail())
              .withExpiresAt(genExpirationDateRefreshToken())
              .sign(algorithm);

    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while generating token", exception);
    }
  }

  public String getAuthentication(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
              .withIssuer("leonidas-API")
              .build()
              .verify(token)
              .getSubject();

    } catch (JWTVerificationException exception){
      return "";
    }
  }

  public boolean validateToken(String token) {
    DecodedJWT decodeJWT = decodedToken(token);

    try {
      if (decodeJWT.getExpiresAt().before(new Date())) {
        return false;
      }
      return true;
    } catch (Exception e) {
      throw new Error("InvalidToken");
    }
  }

  public DecodedJWT decodedToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT;
  }


  private Instant genExpirationDateRefreshToken(){
    return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
  }

  private Instant genExpirationAccessToken(){
    return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
  }




}
