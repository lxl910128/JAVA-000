package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.demo.api.domain.Order;
import io.kimmking.rpcfx.demo.api.server.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
