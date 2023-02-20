package com.anobel.service.impl;

import com.anobel.model.Order;
import com.anobel.repository.OrderRepository;
import com.anobel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findCurrentClientOrders(long id) {
        return orderRepository.getByClientId(id);
    }
    @Override
    public List<Order>findAll(){
        return orderRepository.findAll();
    }
}
