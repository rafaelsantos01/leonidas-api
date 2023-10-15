package com.spantanTech.leonidas.security.authorizationService;

import com.spantanTech.leonidas.modules.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = usersRepository.findByUserMail(username);

    if(user != null ){
      return user;
    }else{
      throw new UsernameNotFoundException("UserNotFound");
    }
  }
}
