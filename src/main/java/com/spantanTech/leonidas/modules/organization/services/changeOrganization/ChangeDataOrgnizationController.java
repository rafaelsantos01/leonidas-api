package com.spantanTech.leonidas.modules.organization.services.changeOrganization;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Organização")
@RestController
@RequestMapping("organization/{id_organization}")
public class ChangeDataOrgnizationController {


    @Autowired
    ChangeDataOrgnizationService changeDataOrgnizationService;

    @ApiOperation("Endpoint responsável por atualizar os dados da organização")
    @Tag(name = "Organização")
    @PutMapping()
    @ResponseBody
    public ResponseEntity<Void> handle(@PathVariable("id_organization")  String id_organization){

        changeDataOrgnizationService.execute(id_organization);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
