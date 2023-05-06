package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamRepository extends JpaRepository<Ram,Long> {
    @Query(value = "SELECT ram FROM Ram ram WHERE ram.ram_type = ?1 and ram.speed = ?2")
    List<Ram>getCompatible(String ram_type,Integer ram_speed);
}
