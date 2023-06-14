package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamRepository extends JpaRepository<Ram,Long> {
    @Procedure(procedureName = "get_comp_ram")
    List<Ram> getCompatible(@Param("motherboard_id") Integer motherboardId);
}
