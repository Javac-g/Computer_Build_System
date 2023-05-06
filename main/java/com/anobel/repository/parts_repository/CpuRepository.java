package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
    @Query(value = "SELECT cpu FROM Cpu  cpu WHERE cpu.socket = ?1")
    List<Cpu> getCompatible(String socket);
}
