package com.blogapp.api.repositries;

import com.blogapp.api.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositry extends JpaRepository<Roles,Integer> {
}
