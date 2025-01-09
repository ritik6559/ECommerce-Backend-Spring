package com.ritik.dreamshop.service.order;

import com.ritik.dreamshop.dto.order.OrderDto;
import com.ritik.dreamshop.model.order.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
