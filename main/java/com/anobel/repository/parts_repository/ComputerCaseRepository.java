package com.anobel.repository.parts_repository;

import com.anobel.model.parts.ComputerCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerCaseRepository extends JpaRepository<ComputerCase,Long> {
    @Query(value = "SELECT Ccase FROM ComputerCase Ccase WHERE Ccase.max_cpu_cooler_height >= ?1  and Ccase.max_gpu_length >= ?2 and Ccase.max_psu_length >= ?3")
    List<ComputerCase> getCompatible(Integer cpu_cooler_h,Integer gpu_length,Integer psu_length);
}
