package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerSupplyRepository extends JpaRepository<Storage,Long> {

   @Procedure(procedureName = "get_comp_power")
    List<PowerSupply> getCompatiblePowerSupplies(
        @Param("motherboard_id") Integer motherboardId,
        @Param("cpu_id") Integer cpuId,
        @Param("ram_id") Integer ramId,
        @Param("gpu_id") Integer gpuId,
        @Param("storage_id") Integer storageId
    );
}
