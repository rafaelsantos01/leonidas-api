package com.spantanTech.leonidas.modules.users.services.Authentication.login;

import com.spantanTech.leonidas.modules.users.services.Authentication.dto.AuthenticationDTO;
import com.spantanTech.leonidas.modules.users.services.Authentication.dto.LoginResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("auth")
public class AuthenticationLoginController {

  @Autowired
  AuthenticationLoginService authenticationLoginService;


  @ApiOperation("Endpoint responsável por realizar o login de um usuário ja cadastrado")
  @Tag(name = "Autenticação")
  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){

    LoginResponseDTO token = authenticationLoginService.handle(data);

    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}

