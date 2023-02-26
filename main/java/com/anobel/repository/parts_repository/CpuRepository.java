package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
}
