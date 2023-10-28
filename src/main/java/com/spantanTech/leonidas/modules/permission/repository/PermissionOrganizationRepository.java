package com.spantanTech.leonidas.modules.permission.repository;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.modules.permission.entities.Permission;
import com.spantanTech.leonidas.modules.permission.entities.PermissionOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionOrganizationRepository extends JpaRepository<PermissionOrganization, UUID> {
    PermissionOrganization findByOrganizationAndPermissionAndStatusTrue(Organization organization, Permission permission);
}