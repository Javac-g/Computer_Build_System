package com.anobel.service.impl;

import com.anobel.model.Active_Orders;
import com.anobel.model.Client;
import com.anobel.model.Order;
import com.anobel.repository.ActiveOrdersRepository;
import com.anobel.service.ActiveOrdersService;
import org.springframework.beans.factory.annotation.Autowired;

public class ActiveOrdersServiceImpl implements ActiveOrdersService {
    @Autowired
    private  ActiveOrdersRepository activeOrdersRepository;

    @Override
    public Client getClientByOrderId(Long order_id) {
        Active_Orders active = activeOrdersRepository.getClientByOrder_id(order_id);
        return active.getClient();
    }

    @Override
    public Order getOrderByClientId(Long client_id) {
        Active_Orders active = activeOrdersRepository.getOrderByClient_id(client_id);
        return active.getOrder();
    }
}
