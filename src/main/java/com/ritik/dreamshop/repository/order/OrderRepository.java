package com.ritik.dreamshop.repository.order;

import com.ritik.dreamshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
