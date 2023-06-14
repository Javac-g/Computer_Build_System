package com.anobel.repository.parts_repository;

import com.anobel.model.parts.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {

   @Procedure(procedureName = "get_comp_storage")
    List<Storage>getCompatible(@Param("motherboard_id") Integer motherboardId);
}
