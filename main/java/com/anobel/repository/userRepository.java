package com.anobel.repository;

import com.anobel.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<Client,Long> {
    Client findByEmail(String email);
}
