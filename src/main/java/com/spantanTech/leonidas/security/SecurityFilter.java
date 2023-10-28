package com.spantanTech.leonidas.security;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.spantanTech.leonidas.config.errors.implementatins.ErrorAuth;
import com.spantanTech.leonidas.modules.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter{

  @Autowired
  TokenService tokenService;
  @Autowired
  UsersRepository userRepository;

  @Autowired
  private ErrorAuth errorAuth;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try{
      String token = this.recoverToken(request);
      if(token != null && tokenService.validateToken(token)){
        String login = tokenService.getAuthentication(token);
        UserDetails user = userRepository.findByUserMail(login);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }catch (TokenExpiredException exception) {
      errorAuth.tokenExpired(response);
      return;
    } catch (InsufficientAuthenticationException exception) {
      errorAuth.errorAuth(response);
      return;
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request){
    String authHeader = request.getHeader("Authorization");

    if(authHeader == null ) {
      return null;
    }
    return authHeader.replace("Bearer ", "");
  }
}
