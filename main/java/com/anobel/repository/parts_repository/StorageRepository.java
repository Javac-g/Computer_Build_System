package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {

    @Query(value = "Select storage FROM Storage storage WHERE storage.s_interface = ?1")
    List<Storage>getCompatible(String sata_type);
}
