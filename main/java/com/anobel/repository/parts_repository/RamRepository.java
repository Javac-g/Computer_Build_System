package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<Ram,Long> {
}
