package com.spantanTech.leonidas.modules.users.services.Authentication.refresh;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("auth")
public class RefreshTokenController {


    @ApiOperation("Endpoint responsável por agerar um novo token para o usuário")
    @Tag(name = "Autenticação")
    @PutMapping("/refresh")
    @ResponseBody
    public ResponseEntity<Void> register(@RequestBody @Valid String token){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
