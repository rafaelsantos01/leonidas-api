package com.spantanTech.leonidas.security.context;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.modules.organization.entities.UserOrganization;
import com.spantanTech.leonidas.modules.organization.repository.OrganizationRepository;
import com.spantanTech.leonidas.modules.organization.repository.UserOrganizationRepository;
import com.spantanTech.leonidas.modules.users.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SetUserSession {

  public Users execute(){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Users userAuth = (Users) authentication.getPrincipal();

    Users users = new Users();
    users.setId(userAuth.getId());

    return users;
  }


}
