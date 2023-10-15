package com.spantanTech.leonidas.modules.users.repository;


import com.spantanTech.leonidas.modules.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    UserDetails findByUserMail(String login);

}
