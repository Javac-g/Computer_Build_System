package com.anobel.repository;

import com.anobel.model.Active_Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveOrdersRepository extends JpaRepository<Active_Orders,Long> {
    @Query(value = "SELECT active FROM Active_Orders active WHERE active.order.id = ?1")
    Active_Orders getOrderByClient_id(Long client_id);

    @Query(value = "SELECT entity FROM Active_Orders entity WHERE entity.order.id = ?1")
    Active_Orders getClientByOrder_id(Long order_id);

}
