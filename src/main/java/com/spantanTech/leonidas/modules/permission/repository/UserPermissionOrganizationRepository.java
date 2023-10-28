package com.spantanTech.leonidas.modules.permission.repository;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.modules.permission.entities.Permission;
import com.spantanTech.leonidas.modules.permission.entities.PermissionOrganization;
import com.spantanTech.leonidas.modules.permission.entities.UserPermissionOrganization;
import com.spantanTech.leonidas.modules.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPermissionOrganizationRepository extends JpaRepository<UserPermissionOrganization, UUID> {
    UserPermissionOrganization findByUserAndOrganizationAndPermissionOrganization(Users user, Organization organization, PermissionOrganization permissionOrganization);
}