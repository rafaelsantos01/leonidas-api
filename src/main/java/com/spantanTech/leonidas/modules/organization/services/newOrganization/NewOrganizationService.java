package com.spantanTech.leonidas.modules.organization.services.newOrganization;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.modules.organization.repository.OrganizationRepository;
import com.spantanTech.leonidas.modules.organization.services.newOrganization.dto.NewOrganizationDTO;
import com.spantanTech.leonidas.modules.permission.entities.Permission;
import com.spantanTech.leonidas.modules.permission.entities.PermissionOrganization;
import com.spantanTech.leonidas.modules.permission.entities.UserPermissionOrganization;
import com.spantanTech.leonidas.modules.permission.repository.PermissionOrganizationRepository;
import com.spantanTech.leonidas.modules.permission.repository.PermissionRepository;
import com.spantanTech.leonidas.modules.permission.repository.UserPermissionOrganizationRepository;
import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.security.context.SetUserSession;
import com.spantanTech.leonidas.utils.CpfAndCnpjValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewOrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    CpfAndCnpjValidation cpfAndCnpjValidation;

    @Autowired
    SetUserSession setUserSession;

    @Autowired
    PermissionOrganizationRepository permissionOrganizationRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    UserPermissionOrganizationRepository userPermissionOrganizationRepository;


    public void execute(NewOrganizationDTO data) {
        Users user = setUserSession.execute();

        if (data.getCnpj() == null || !cpfAndCnpjValidation.isValidCnpj(data.getCnpj())) {
            throw new Error("CNPJInvalid");
        }

        if (organizationRepository.existsByCnpj(data.getCnpj())) {
            throw new Error("CNPJAlreadyExists");
        }

        Organization organization = new Organization();
        organization.setOrganizationName(data.getOrganization_name());
        organization.setCnpj(data.getCnpj());
        organization.setEmail(data.getEmail());
        organization.setFantasyName(data.getFantasy_name());
        organization.setStatus(data.isStatus());

        Organization organizationSave = organizationRepository.saveAndFlush(organization);

        List<Permission> permissionList = permissionRepository.findAll();
        UserPermissionOrganization userPermissionOrganization = new UserPermissionOrganization();

        for(Permission permission : permissionList){
          PermissionOrganization permissionOrganization = new PermissionOrganization();
          permissionOrganization.setStatus(true);
          permissionOrganization.setOrganization(organizationSave);
          permissionOrganization.setPermission(permission);

          PermissionOrganization permissionOrganizationSave = permissionOrganizationRepository.saveAndFlush(permissionOrganization);
          if(permission.getName().equals("Admin")){
              userPermissionOrganization.setPermissionOrganization(permissionOrganizationSave);
          }
        }

        userPermissionOrganization.setOrganization(organization);
        userPermissionOrganization.setUser(user);
        userPermissionOrganization.setStatus(true);
        userPermissionOrganization.setCreate(true);
        userPermissionOrganization.setInative(true);
        userPermissionOrganization.setUpdate(true);
        userPermissionOrganization.setView(true);

        userPermissionOrganizationRepository.saveAndFlush(userPermissionOrganization);
    }
}
