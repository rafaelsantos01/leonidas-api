package com.spantanTech.leonidas.modules.templatesEmail.repository;

import com.spantanTech.leonidas.modules.templatesEmail.entities.Templates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TemplatesRepository extends JpaRepository<Templates, UUID> {
    Templates findByTemplateTypeAndStatus(int templateType, boolean status);
}