package com.spantanTech.leonidas.modules.users.entities;

import com.spantanTech.leonidas.modules.users.ENUM.UserRole;
import com.spantanTech.leonidas.shared.entities.DateBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "users")
public class Users implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "uuid")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "email")
  private String userMail;

  @Column(name = "password")
  private String password;

  @Column(name = "password_old")
  private String passwordOld;

  @Column(name = "permission")
  private UserRole permission;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "status")
  private boolean status;

  @Column(name = "created_at")
  @CreationTimestamp()
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp()
  private Timestamp updatedAt;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if(this.permission == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return userMail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
