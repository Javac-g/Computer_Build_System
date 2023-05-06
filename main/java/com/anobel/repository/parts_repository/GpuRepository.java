package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GpuRepository extends JpaRepository<Gpu,Long> {

    @Query(value = "Select gpu FROM Gpu  gpu WHERE gpu.pcie_type = ?1")
    List<Gpu> getCompatible(String gpu);

}
