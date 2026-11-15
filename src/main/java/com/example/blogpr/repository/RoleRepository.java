package com.example.blogpr.repository;

import com.example.blogpr.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> getRoleByRoleName(String roleName);
}
