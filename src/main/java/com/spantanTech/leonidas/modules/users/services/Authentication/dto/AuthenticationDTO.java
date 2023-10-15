package com.spantanTech.leonidas.modules.users.services.Authentication.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthenticationDTO {

  @NotNull @NotEmpty @NotBlank
  private String userMail;

  @NotNull @NotEmpty @NotBlank
  private String  password;
}
