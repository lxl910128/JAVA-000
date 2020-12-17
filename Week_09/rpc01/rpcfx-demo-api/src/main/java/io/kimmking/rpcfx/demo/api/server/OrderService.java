package io.kimmking.rpcfx.demo.api.server;

import io.kimmking.rpcfx.demo.api.domain.Order;

public interface OrderService {
    
    Order findOrderById(int id);
    
}
