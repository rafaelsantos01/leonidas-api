package com.spantanTech.leonidas.modules.organization.repository;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    boolean existsByCnpj(String cnpj);
}