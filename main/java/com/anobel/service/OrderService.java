package com.anobel.service;

import com.anobel.model.Order;

import java.util.List;

public interface OrderService {
    List<Order>findAll();
    List<Order> findCurrentClientOrders(long id);
}
