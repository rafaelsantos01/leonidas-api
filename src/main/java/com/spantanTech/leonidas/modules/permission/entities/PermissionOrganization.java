package com.spantanTech.leonidas.modules.permission.entities;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.shared.entities.DateBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "permission_organization")
public class PermissionOrganization extends DateBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
