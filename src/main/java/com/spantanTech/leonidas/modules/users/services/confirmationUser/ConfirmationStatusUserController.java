package com.spantanTech.leonidas.modules.users.services.confirmationUser;

import com.spantanTech.leonidas.modules.users.services.Authentication.dto.AuthenticationDTO;
import com.spantanTech.leonidas.modules.users.services.Authentication.dto.LoginResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@Tag(name = "Autenticação")
@RestController
@RequestMapping("auth")
public class ConfirmationStatusUserController {

    @Autowired
    ConfirmationStatusUserService confirmationStatusUserService;


    @ApiOperation("Endpoint responsável por confirmar o email do usuário cadastrado")
    @Tag(name = "Autenticação")
    @PutMapping("/confirmation/{id_user}")
    @ResponseBody
    public ResponseEntity<LoginResponseDTO> handle(@PathVariable("id_user") UUID id_user){

        confirmationStatusUserService.execute(id_user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}


