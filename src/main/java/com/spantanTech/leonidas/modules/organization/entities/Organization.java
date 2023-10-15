package com.spantanTech.leonidas.modules.organization.entities;


import com.spantanTech.leonidas.shared.entities.DateBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "organization")
public class Organization extends DateBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "organization_name")
    @NotNull
    private String organizationName;

    @Column(name = "fantasy_name")
    @NotNull
    private String fantasyName;

    @Column(name = "status")
    private boolean status;

    @Column(name = "cnpj")
    @NotNull
    private String cnpj;

    @Column(name = "email")
    @NotNull
    private String email;

}
