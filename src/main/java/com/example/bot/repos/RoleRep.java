package com.example.bot.repos;

import com.example.bot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRep extends JpaRepository<Role, Long> {
}
