package com.anobel.service;

import com.anobel.model.*;

import java.util.List;

public interface OrderService {
    List<Order>findAll();
    List<Order> findCurrentClientOrders(long id);
	Order findById(long id);
    void create(Client client,Order order);
    Order update(long id,Order order);
    void delete(long id);
}
