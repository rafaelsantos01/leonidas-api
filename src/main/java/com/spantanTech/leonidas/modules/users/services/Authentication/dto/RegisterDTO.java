package com.spantanTech.leonidas.modules.users.services.Authentication.dto;

import com.spantanTech.leonidas.modules.users.ENUM.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterDTO {

  @NotNull @NotBlank @NotEmpty
  private String name;

  private String cpf;

  @NotNull @NotBlank @NotEmpty
  private String userMail;

  private String phoneNumber;

  @NotNull @NotBlank @NotEmpty
  private String password;

  @NotNull @NotBlank @NotEmpty
  private String password_confirmation;


  private UserRole permission;
}
