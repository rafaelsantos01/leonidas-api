package com.spantanTech.leonidas.modules.organization.entities;

import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.shared.entities.DateBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "user_organization")
public class UserOrganization extends DateBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "status")
    private boolean status;
}
