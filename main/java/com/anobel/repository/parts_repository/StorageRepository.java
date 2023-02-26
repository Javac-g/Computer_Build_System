package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
}
