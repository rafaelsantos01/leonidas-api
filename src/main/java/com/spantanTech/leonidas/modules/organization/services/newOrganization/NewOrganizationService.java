package com.spantanTech.leonidas.modules.organization.services.newOrganization;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.modules.organization.repository.OrganizationRepository;
import com.spantanTech.leonidas.modules.organization.services.newOrganization.dto.NewOrganizationDTO;
import com.spantanTech.leonidas.utils.CpfAndCnpjValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewOrganizationService {


    @Autowired
    OrganizationRepository organizationRepository;


    @Autowired
    CpfAndCnpjValidation cpfAndCnpjValidation;

    public void execute(NewOrganizationDTO data) {

        if(data.getCnpj() == null || !cpfAndCnpjValidation.isValidCnpj(data.getCnpj())){
            throw  new Error("CNPJInvalid");
        }

      if(organizationRepository.existsByCnpj(data.getCnpj())){
          throw  new Error("CNPJAlreadyExists");
      }

      Organization organization = new Organization();
      organization.setOrganizationName(data.getOrganization_name());
      organization.setCnpj(data.getCnpj());
      organization.setEmail(data.getEmail());
      organization.setFantasyName(data.getFantasy_name());
      organization.setStatus(data.isStatus());

      organizationRepository.saveAndFlush(organization);
    }
}
