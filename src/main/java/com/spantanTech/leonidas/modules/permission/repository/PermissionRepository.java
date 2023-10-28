package com.spantanTech.leonidas.modules.permission.repository;

import com.spantanTech.leonidas.modules.permission.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Permission findByNameAndStatusTrue(String name);
}