package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard,Long> {
    @Query(value = "Select mb FROM Motherboard mb WHERE mb.socket_cpu = ?1 and mb.ram_speed = ?2 and mb.ram_type = ?3 and mb.gpu_type = ?4")
    List<Motherboard> getCompatible(String socket,Integer ram_speed,String ram_type , String gpu_type);
}
