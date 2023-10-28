package com.spantanTech.leonidas.modules.permission.entities;

import com.spantanTech.leonidas.modules.organization.entities.Organization;
import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.shared.entities.DateBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "user_permission_organization")
public class UserPermissionOrganization extends DateBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "permission_organization_id")
    private PermissionOrganization permissionOrganization;

    @Column(name = "status")
    private boolean status;

    @Column(name = "can_view")
    private boolean view;

    @Column(name = "can_update")
    private boolean update;

    @Column(name = "can_create")
    private boolean create;

    @Column(name = "can_inative")
    private boolean inative;
}
