package com.anobel.service;

import com.anobel.model.Order;

import java.util.List;

public interface OrderService {
    List<Order>findAll();
    List<Order> findCurrentClientOrders(long id);
	Order findById(long id);
    Order create(long client_id,Order order);
    Order update(long id,Order order);
    void delete(long id);
}
