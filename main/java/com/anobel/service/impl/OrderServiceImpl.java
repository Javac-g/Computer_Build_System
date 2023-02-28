package com.anobel.service.impl;

import com.anobel.model.Active_Orders;
import com.anobel.model.Client;
import com.anobel.model.Order;
import com.anobel.repository.ActiveOrdersRepository;
import com.anobel.repository.OrderRepository;
import com.anobel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ActiveOrdersRepository activeOrdersRepository;

    @Autowired
    private ClientServiceImpl clientService;


    @Override
    public List<Order> findCurrentClientOrders(long id) {
        Client x = clientService.readById(id);
        if (x != null){
            return x.getOrders();
        }
        return null;
    }

    @Override
    public List<Order>findAll(){
        return orderRepository.findAll();
    }
	@Override
	public Order findById(long id){
		return orderRepository.findById(id).get();
		
	}

    @Override
    public Order create(long client_id,Order order) {
        Active_Orders active_orders = new Active_Orders();
        active_orders.setClient(clientService.readById(client_id));
        active_orders.setOrder(order);
        activeOrdersRepository.save(active_orders);

        return orderRepository.save(order);

    }

    @Override
    public Order update(long id, Order order) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
