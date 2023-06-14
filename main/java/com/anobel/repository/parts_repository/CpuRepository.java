package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
    @Procedure(procedureName = "get_comp_cpu")
    List<Cpu> getCompatible(@Param("motherboard_id") Integer motherboardId);
}
