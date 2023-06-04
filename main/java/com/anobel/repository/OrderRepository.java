package com.anobel.repository;

import com.anobel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
	@Query(value = "SELECT o FROM Order o ORDER BY o.id DESC")
    Order findLastOrder();
	
	Order findTopByOrderByIdDesc();
	
    @Query(value = "SELECT oreder_id FROM active_orders WHERE client_id = :id ", nativeQuery = true)
    List<Order> getByClientId(@Param("id") long userId);
	

}
