package com.spantanTech.leonidas.modules.users.services.Authentication.register;


import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.modules.users.repository.UsersRepository;
import com.spantanTech.leonidas.modules.users.services.Authentication.dto.RegisterDTO;
import com.spantanTech.leonidas.utils.CpfAndCnpjValidation;
import com.spantanTech.leonidas.utils.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewUserService {

  @Autowired
  private UsersRepository usersRepository;


  @Autowired
  private PasswordValidation passwordValidation;

  @Autowired
  CpfAndCnpjValidation cpfAndCnpjValidation;


  public void register(RegisterDTO data){
    if(usersRepository.findByUserMail(data.getUserMail()) != null) {
        throw new Error("UserAlreadyExists");
    }

    if(data.getCpf() != null && !cpfAndCnpjValidation.isCPF(data.getCpf())){
      throw new Error("CPFInvalid");
    }

    if(!data.getPassword().equals(data.getPassword_confirmation())){
      throw new Error("PasswordsNotMatch");
    }

    if(!passwordValidation.execute(data.getPassword())){
      throw new Error("PasswordsNotMeetMinimumRequirements");
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

    Users newUser = new Users();

    newUser.setName(data.getName());
    newUser.setCpf(data.getCpf());
    newUser.setUserMail(data.getUserMail());
    newUser.setPassword(encryptedPassword);
    newUser.setPasswordOld(encryptedPassword);
    newUser.setStatus(false);
    newUser.setPermission(data.getPermission());

     usersRepository.saveAndFlush(newUser);
  }
}
