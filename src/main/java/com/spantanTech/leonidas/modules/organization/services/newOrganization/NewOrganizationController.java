package com.spantanTech.leonidas.modules.organization.services.newOrganization;

import com.spantanTech.leonidas.modules.organization.services.newOrganization.dto.NewOrganizationDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Organização")
@RestController
@RequestMapping("organization")
public class NewOrganizationController {

    @Autowired
    NewOrganizationService newOrganizationService;

    @ApiOperation("Endpoint responsável por cadastrar uma nova organização")
    @Tag(name = "Organização")
    @PostMapping()
    @ResponseBody
    public ResponseEntity<Void> register(@RequestBody @Valid NewOrganizationDTO data){

        newOrganizationService.execute(data);

        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}

