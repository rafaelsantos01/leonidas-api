package com.spantanTech.leonidas.modules.organization.services.newOrganization.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewOrganizationDTO {

    @NotBlank @NotNull @NotEmpty
    private String organization_name;

    @NotBlank @NotNull @NotEmpty
    private String fantasy_name;

    @NotBlank @NotNull @NotEmpty
    private String cnpj;

    @NotBlank @NotNull @NotEmpty
    private String email;

    private boolean status;



}
