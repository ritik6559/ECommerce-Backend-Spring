package com.ritik.dreamshop.service.order;

import com.ritik.dreamshop.model.order.Order;

public interface IOrderService {

    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

}
