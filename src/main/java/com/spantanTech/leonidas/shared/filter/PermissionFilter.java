package com.spantanTech.leonidas.shared.filter;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.modules.organization.repository.OrganizationRepository;
import com.spantanTech.leonidas.modules.organization.repository.UserOrganizationRepository;
import com.spantanTech.leonidas.modules.permission.entities.PermissionOrganization;
import com.spantanTech.leonidas.modules.permission.entities.UserPermissionOrganization;
import com.spantanTech.leonidas.modules.permission.repository.PermissionOrganizationRepository;
import com.spantanTech.leonidas.modules.permission.repository.PermissionRepository;
import com.spantanTech.leonidas.modules.permission.repository.UserPermissionOrganizationRepository;
import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.security.context.SetOrgnizationSession;
import com.spantanTech.leonidas.security.context.SetUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionFilter {

    @Autowired
    UserOrganizationRepository userOrganizationRepository;


    @Autowired
    OrganizationRepository organizationRepository;


    @Autowired
    PermissionRepository permissionRepository;


    @Autowired
    PermissionOrganizationRepository permissionOrganizationRepository;

    @Autowired
    UserPermissionOrganizationRepository userPermissionOrganizationRepository;

    @Autowired
    SetUserSession setUserSession;


    @Autowired
    SetOrgnizationSession setOrgnizationSession;

    //@PreAuthorize("@permissionFilter.getPermission('Organization', 'create')")

    public boolean getPermission(String permissionData, String action) {
        Users user = setUserSession.execute();
        Organization org = setOrgnizationSession.getUserOrg();

        if(isAdmin(user,org)){
            return true;
        }

        com.spantanTech.leonidas.modules.permission.entities.Permission permission = permissionRepository.findByNameAndStatusTrue(permissionData);
        if (permission == null) {
            throw new Error("PermissionNotFound");
        }

        PermissionOrganization organizationAndPermission = permissionOrganizationRepository.findByOrganizationAndPermissionAndStatusTrue(org, permission);
        if (organizationAndPermission == null) {
            throw new Error("PermissionOrganizationNotFound");
        }

        UserPermissionOrganization userPermissionOrganization = userPermissionOrganizationRepository.findByUserAndOrganizationAndPermissionOrganization(user, org, organizationAndPermission);
        if (userPermissionOrganization == null || !userPermissionOrganization.isStatus()) {
            throw new Error("PermissionNotFound");
        }

        if(action.equals("view")){
            return userPermissionOrganization.isView();
        }else if(action.equals("update")){
            return userPermissionOrganization.isUpdate();
        }else if(action.equals("create")){
            return userPermissionOrganization.isCreate();
        }else if(action.equals("inative")){
            return userPermissionOrganization.isInative();
        }else{
            return false;
        }
    }

    public boolean isAdmin(Users user,Organization org){
        com.spantanTech.leonidas.modules.permission.entities.Permission permissionAdmin = permissionRepository.findByNameAndStatusTrue("Admin");
        if(permissionAdmin == null){
            return false;
        }
        PermissionOrganization organizationAndPermissionAdmin = permissionOrganizationRepository.findByOrganizationAndPermissionAndStatusTrue(org, permissionAdmin);
        if(organizationAndPermissionAdmin == null){
            return false;
        }
        UserPermissionOrganization userPermissionOrganizationAdmin = userPermissionOrganizationRepository.findByUserAndOrganizationAndPermissionOrganization(user, org, organizationAndPermissionAdmin);

        if(userPermissionOrganizationAdmin != null && userPermissionOrganizationAdmin.isStatus()){
            return true;
        }else{
            return false;
        }
    }
}
