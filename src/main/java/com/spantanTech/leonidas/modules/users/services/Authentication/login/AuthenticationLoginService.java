package com.spantanTech.leonidas.modules.users.services.Authentication.login;

import com.spantanTech.leonidas.modules.organization.entities.UserOrganization;
import com.spantanTech.leonidas.modules.organization.repository.UserOrganizationRepository;
import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.modules.users.repository.UsersRepository;
import com.spantanTech.leonidas.modules.users.services.Authentication.dto.AuthenticationDTO;
import com.spantanTech.leonidas.modules.users.services.Authentication.dto.LoginResponseDTO;
import com.spantanTech.leonidas.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

@Service
public class AuthenticationLoginService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UsersRepository repository;

  @Autowired
  private TokenService tokenService;


//  public LoginResponseDTO handle(AuthenticationDTO data){
//    LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
//
//    try {
//      UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
//      Authentication auth = authenticationManager.authenticate(usernamePassword);
//
//      String token = tokenService.generateToken((Users) auth.getPrincipal());
//
//      loginResponseDTO.setAccess_token(token);
//      loginResponseDTO.setRefresh_token(token);
//    }catch (Exception error){
//        throw new Error("LoginFail");
//    }
//
//    return loginResponseDTO;
//  }
//}


  public LoginResponseDTO handle(AuthenticationDTO data) {
    LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

    try {
      UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getUserMail(), data.getPassword());
      Authentication auth = authenticationManager.authenticate(usernamePassword);

      if (auth.isAuthenticated()) {
        List<String> permissions = auth.getAuthorities()
          .stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList());


        String access = tokenService.generateToken((Users) auth.getPrincipal());
        String refresh = tokenService.generateRefreshToken((Users) auth.getPrincipal());

        loginResponseDTO.setAccess_token(access);
        loginResponseDTO.setRefresh_token(refresh);

        loginResponseDTO.setPermission(permissions);
      } else {
        throw new Error("LoginFail");
      }
    } catch (Exception error) {
      throw new Error("LoginFail");
    }

    return loginResponseDTO;
  }
}
