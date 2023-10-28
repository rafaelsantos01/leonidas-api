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
public class SetOrgnizationSession {

    @Autowired
    UserOrganizationRepository userOrganizationRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    public Organization getUserOrg(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users userAuth = (Users) authentication.getPrincipal();

        UserOrganization userAndStatus = userOrganizationRepository.findByUserAndStatus(userAuth, true);

        Organization organization = organizationRepository.findById(userAndStatus.getOrganization().getId()).orElse(null);
        if(organization == null){
            throw new Error("OrganizationNotFound");
        }

        return organization;
    }
}
