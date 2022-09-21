package com.team.art.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.art.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
