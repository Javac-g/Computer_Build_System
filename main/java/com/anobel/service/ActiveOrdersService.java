package com.anobel.service;

import com.anobel.model.Client;
import com.anobel.model.Order;

public interface ActiveOrdersService {
     Client getClientByOrderId(Long id);
     Order getOrderByClientId(Long id);
}
