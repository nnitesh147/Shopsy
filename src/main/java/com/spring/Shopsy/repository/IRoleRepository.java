package com.spring.Shopsy.repository;

import com.spring.Shopsy.model.AppRole;
import com.spring.Shopsy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole role);

}
