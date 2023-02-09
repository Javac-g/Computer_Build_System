package com.anobel.repository;

import com.anobel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
