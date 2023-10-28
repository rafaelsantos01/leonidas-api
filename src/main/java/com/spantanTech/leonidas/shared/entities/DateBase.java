package com.spantanTech.leonidas.shared.entities;


import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.security.context.SetUserSession;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public class DateBase {

  @Column(name = "created_at")
  @CreationTimestamp()
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp()
  private Timestamp updatedAt;

  @ManyToOne
  @JoinColumn(name = "user_updated")
  private Users userUpdated;

  @ManyToOne
  @JoinColumn(name = "user_created")
  private Users userCreated;

  @PrePersist
  private void beforePersist(){
    SetUserSession getDataSession = new SetUserSession();

    if(this.userCreated == null || this.userUpdated == null){
      Users users = getDataSession.execute();
      this.userCreated = users;
      this.userUpdated = users;
    }
  }

  @PreUpdate
  private void beforeUpdate(){

    SetUserSession getDataSession = new SetUserSession();
    if( this.userUpdated == null){
        this.userUpdated = getDataSession.execute();
    }

  }

}
