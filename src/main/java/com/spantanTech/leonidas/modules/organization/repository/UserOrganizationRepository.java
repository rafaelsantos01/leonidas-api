package com.spantanTech.leonidas.modules.organization.repository;

import com.spantanTech.leonidas.modules.organization.entities.UserOrganization;
import com.spantanTech.leonidas.modules.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserOrganizationRepository extends JpaRepository<UserOrganization, UUID> {
    UserOrganization findByUserAndStatus(Users user, boolean status);

}