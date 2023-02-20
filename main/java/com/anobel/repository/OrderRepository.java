package com.anobel.repository;

import com.anobel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT oreder_id FROM active_orders WHERE client_id = :id ", nativeQuery = true)
    List<Order> getByClientId(@Param("id") long userId);

}
