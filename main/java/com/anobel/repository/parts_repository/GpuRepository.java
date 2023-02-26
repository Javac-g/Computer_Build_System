package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpuRepository extends JpaRepository<Gpu,Long> {

}
