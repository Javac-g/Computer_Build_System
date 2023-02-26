package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard,Long> {
}
