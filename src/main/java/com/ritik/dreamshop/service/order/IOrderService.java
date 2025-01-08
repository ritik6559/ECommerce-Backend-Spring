package com.ritik.dreamshop.service.order;

import com.ritik.dreamshop.model.order.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
