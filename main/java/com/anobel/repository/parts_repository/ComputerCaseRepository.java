package com.anobel.repository.parts_repository;

import com.anobel.model.parts.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerCaseRepository extends JpaRepository<ComputerCase,Long> {
    @Procedure(procedureName = "get_comp_case")
    List<ComputerCase> getCompatible(
        @Param("motherboard_id") Integer motherboardId,
        @Param("cooler_id") Integer coolerId,
        @Param("gpu_id") Integer gpuId,
        @Param("psu_id") Integer psuId
    );
}
