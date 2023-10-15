package com.spantanTech.leonidas.modules.users.services.Authentication.register;

import com.spantanTech.leonidas.modules.users.services.Authentication.dto.RegisterDTO;
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
public class RegisterNewUserController {

  @Autowired
  RegisterNewUserService authenticationRegisterService;

  @ApiOperation("Endpoint responsável por cadastrar um novo usuário")
  @Tag(name = "Autenticação")
  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){

     authenticationRegisterService.register(data);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
